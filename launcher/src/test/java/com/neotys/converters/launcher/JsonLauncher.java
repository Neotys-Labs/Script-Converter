package com.neotys.converters.launcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.neotys.neoload.model.ImmutableProject;
import com.neotys.neoload.model.Project;
import com.neotys.neoload.model.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonLauncher {

    static Logger logger = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new GuavaModule());
        objectMapper.registerModule(new Jdk8Module());

        ImmutableUserPath user = ImmutableUserPath.builder()
                .name("MyUser")
                .initContainer(ImmutableContainerForMulti.builder().name("init").build())
                .actionsContainer(ImmutableContainerForMulti.builder().name("Action")
                        .addChilds(ImmutableDelay.builder().name("delay").delay("1000").build(), ImmutablePage.builder().name("myPage").thinkTime(0).build())
                        .build())
                .endContainer(ImmutableContainerForMulti.builder().name("end").build())
                .build();

        ImmutableProject project = Project.builder()
                .name("MyProject")
                .addUserPaths(user)
                .build();
        try {
            String json = objectMapper.writeValueAsString(project);
            logger.info(json);
        } catch (Exception e) {
            logger.error("error creating json content",e);
        }

        try {
            InputStream stream = JsonLauncher.class.getResourceAsStream("project.json");
            Project readProject = objectMapper.readValue(stream, Project.class);
            stream.close();
            logger.info(readProject.toString());
        }catch(Exception e) {
            logger.error("error reading the json file",e);
        }
    }


}
