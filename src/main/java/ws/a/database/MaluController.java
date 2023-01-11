/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.a.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@CrossOrigin
//@ResponseBody
public class MaluController {
    
    A2020ws data = new A2020ws();
    A2020wsJpaController control = new A2020wsJpaController();
    
    @GetMapping(value = "/GET", produces = APPLICATION_JSON_VALUE)
    public List<A2020ws> getData(){
        List<A2020ws> buffer = new ArrayList<>();
        buffer = control.findA2020wsEntities();
        
        return buffer;
    }
    
    @PostMapping(value = "/POST", consumes = APPLICATION_JSON_VALUE)
    public String sendData(HttpEntity<String> datasend) throws JsonProcessingException{
        
        String feedback = "Do Nothing";
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(datasend.getBody(), A2020ws.class);
        try{
            control.create(data);
            feedback = data.getName() + " Saved";
        } catch (Exception Error) {
            feedback = Error.getMessage();
        }
        return feedback;
    }
    
    @PutMapping(value = "/PUT", consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> datasend) throws JsonProcessingException{
        
        String feedback = "Do Nothing";
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(datasend.getBody(), A2020ws.class);
        try{
            control.edit(data);
            feedback = data.getName() + " Edited";
        } catch (Exception Error) {
            feedback = Error.getMessage();
        }
        return feedback;
    }
    
    @DeleteMapping(value = "/DELETE", consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> datasend) throws JsonProcessingException{
        
        String feedback = "Do Nothing";
        ObjectMapper mapper = new ObjectMapper();
        data = mapper.readValue(datasend.getBody(), A2020ws.class);
        try{
            control.destroy(data.getId());
            feedback = "Data has been Deleted";
        } catch (Exception Error) {
            feedback = Error.getMessage();
        }
        return feedback;
    }
    
    
}
