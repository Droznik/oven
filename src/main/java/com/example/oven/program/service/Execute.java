package com.example.oven.program.service;
import com.example.oven.util.ApplianceState;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import com.example.oven.program.Baking;
import com.example.oven.program.WarmUp;
import com.example.oven.program.Oven;
import com.example.oven.program.Program;
import com.example.oven.service.BakingService;
import com.example.oven.service.OvenService;
import com.example.oven.service.ProgramService;
import com.example.oven.service.WarmUpService;
import com.example.oven.program.Process;

@Service
@Scope("singleton")
public class Execute {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Program program;
    private StringBuffer status = new StringBuffer(ApplianceState.WAITING.toString());
    private Date operationEnd;
    private Date programEnd;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private Future<Boolean> future;

    public void startProgram() throws InterruptedException {

        if (!isRunning.get()) {
            isRunning.set(true);
            status = new StringBuffer(ApplianceState.RUNNING.toString());

            WarmUp warmUp = program.getWarmUp();
            Baking baking = program.getBaking();

            programEnd = new Date(System.currentTimeMillis() +
                    warmUp.getDuration() +
                    baking.getDuration()
            );

            future = executor.submit(createProcessRunner(warmUp, baking), true);
        }
    }

    public void stopProgram() {

        if (isRunning.get()) {
            isRunning.set(false);
            future.cancel(true);
            status = new StringBuffer(ApplianceState.STOPPED.toString());
        }
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String getStatus() {

        if (isRunning.get() && operationEnd != null && programEnd != null) {

            return status.toString() + getTimeEstimation();
        }

        return status.toString();
    }

    private String getTimeEstimation() {
        Date now = new Date();

        return ",\n\t\toperation estimation: " + getMinutesAndSecondsString(now, operationEnd) +
                ",\n\t\tprogram estimation: " + getMinutesAndSecondsString(now, programEnd);
    }

    private String getMinutesAndSecondsString(Date now, Date end) {
        long l = end.getTime() - now.getTime();
        if (l <= 0) return "00:00";

        long minutes = TimeUnit.MILLISECONDS.toMinutes(l);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(l);
        return minutes + ":" + seconds;
    }

    private Runnable createProcessRunner(WarmUp warmUp, Baking baking) {
        return () -> {
            runProcess(warmUp);
            runProcess(baking);

            if (isRunning.get()) {
                isRunning.set(false);
                status = new StringBuffer(ApplianceState.FINISHED.toString());
            }
        };
    }

    private void runProcess(Process process) {
        if (isRunning.get()) {

            operationEnd = new Date(System.currentTimeMillis() + process.getDuration());
            status = new StringBuffer(ApplianceState.RUNNING.toString()).append(", ").append(process);

            try {
                TimeUnit.MILLISECONDS.sleep(process.getDuration());
            } catch (InterruptedException e) {
            }
        }
    }
}
