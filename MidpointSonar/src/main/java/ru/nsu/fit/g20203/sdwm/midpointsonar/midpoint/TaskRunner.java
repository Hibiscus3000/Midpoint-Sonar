package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint;


import com.evolveum.midpoint.client.api.ObjectReference;
import com.evolveum.midpoint.client.api.exception.CommonException;
import com.evolveum.midpoint.client.api.exception.SchemaException;
import com.evolveum.midpoint.client.impl.prism.RestPrismService;
import com.evolveum.midpoint.client.impl.prism.RestPrismServiceBuilder;
import com.evolveum.midpoint.client.impl.prism.RestPrismTaskService;
import com.evolveum.midpoint.xml.ns._public.common.common_3.TaskType;

import java.io.FileNotFoundException;

public class TaskRunner {

    public void run(RestPrismService restPrismService, String oid){
        RestPrismTaskService taskService = new RestPrismTaskService(restPrismService,
                oid);
        try {
            ObjectReference<TaskType> task = taskService.run().post();
            System.out.println(task.toString());
        } catch (CommonException e) {
            throw new RuntimeException(e);
        }
    }
}
