package com.example.oven.program;

import com.example.oven.program.service.Execute;
import com.example.oven.program.service.Initialize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.oven.service.OvenService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "Oven REST Control", description = "Operations with Oven")
public class Controller {
    private final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private final Execute Execute;
    private final OvenService ovenService;
    private final Initialize initialize;

    private Oven oven;

    @Autowired
    public Controller(Execute Execute, OvenService ovenService, Initialize initialize) {
        this.Execute = Execute;
        this.ovenService = ovenService;
        this.initialize = initialize;
        this.oven = initialize.getOven();
    }



    @ApiOperation(value = "View oven state", response = String.class)
    @RequestMapping(value = "/state", method = RequestMethod.GET)
    public String getState() {
        LOGGER.info("State request");
        return Execute.getStatus();
    }

    @ApiOperation(value = "Start oven", response = String.class)
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start() throws InterruptedException {
        try {
            Execute.startProgram();
        } catch (NullPointerException e) {
            return "Set up program first";
        }
        return "Started";
    }

    @ApiOperation(value = "Stop oven", response = String.class)
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public String stop() {
        Execute.stopProgram();
        return "Stopped";
    }

    @ApiOperation(value = "Set up oven program", response = String.class)
    @RequestMapping(value = "/program/(index)", method = RequestMethod.GET)
    public String selectProgramme(@RequestParam Integer index) {

        try {
            List<Program> programs = oven.getPrograms();
            Execute.setProgram(programs.get(index));

        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("No such program");
        }

        return "Program " + index + "is set";
    }

    @ApiOperation(value = "View all oven programs", response = String.class)
    @RequestMapping(value = "/programs", method = RequestMethod.GET)
    public String getAllProgrammes() {
        List<Program> programs = oven.getPrograms();

        if (programs.isEmpty()) return "Programs are empty";

        StringBuilder programsDescription = new StringBuilder();
        int index = 0;

        for (Program program : programs) {
            programsDescription
                    .append("\n").append(index++).append(": ")
                    .append(program.toString());
        }

        return programsDescription.toString();
    }
}