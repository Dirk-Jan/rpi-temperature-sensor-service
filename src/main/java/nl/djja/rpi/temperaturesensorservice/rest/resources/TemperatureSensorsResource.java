package nl.djja.rpi.temperaturesensorservice.rest.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.djja.rpi.temperaturesensorservice.dto.TemperatureSensorDTO;
import nl.djja.rpi.temperaturesensorservice.exceptions.ItemNotFoundException;
import nl.djja.rpi.temperaturesensorservice.exceptions.TemperatureReadingException;
import nl.djja.rpi.temperaturesensorservice.factories.ServiceFactory;
import nl.djja.rpi.temperaturesensorservice.rest.factory.RESTFactory;
import nl.djja.rpi.temperaturesensorservice.temperaturesensor.TemperatureSensor;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

// TODO Define interface for other protocols

@Path("temperaturesensors")
public class TemperatureSensorsResource {

    @GET
    @Path("{serial}")
    @Produces("application/json")
    public Response readTemperature(@PathParam("serial") String serial) {

        try {
            float value = ServiceFactory.getTemperatureSensorService().getTemperature(serial);
            TemperatureSensorDTO dto = new TemperatureSensorDTO();
            dto.temperatureSensorSerial = serial;
            dto.temperature = value;
            return RESTFactory.getResponse(200, dto);
        } catch (TemperatureReadingException e) {
            return RESTFactory.getErrorResponse(500, e.getMessage());
        } catch (ItemNotFoundException e) {
            return RESTFactory.getErrorResponse(404, e.getMessage());
        }
    }

    @POST
    @Path("{serial}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response setTemperature(@PathParam("serial") String serial, String temperatureSensorDTOJSON) {
        // Convert dto to obj
        TemperatureSensorDTO dto;
        try {
            dto = new ObjectMapper().readValue(temperatureSensorDTOJSON, TemperatureSensorDTO.class);
        } catch (IOException e) {
            return RESTFactory.getErrorResponse(500, "Invalid object was supplied");
        }
        ServiceFactory.getTemperatureSensorService().setTemperature(serial, dto.temperature);
        return RESTFactory.get204Response();
    }
}
