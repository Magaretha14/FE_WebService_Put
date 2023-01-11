/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.a.database;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ws.a.database.exceptions.NonexistentEntityException;

/**
 *
 * @author ASUS
 */
@Controller
@ResponseBody
public class PutController {
    
    A2020ws data = new A2020ws();
    A2020wsJpaController actrl = new A2020wsJpaController();
    
    @RequestMapping("/getName/{id}")
    public String getName(@PathVariable("id") int id){
        try {
            data = actrl.findA2020ws(id);
            return data.getName() + "<br>" + data.getTglLahir();
        }
        catch (Exception error){
            
            return "Data tidak ada";
        }
        
    }
    //Delete ById
    @RequestMapping("/delete/{id}")
    public String deleteData(@PathVariable("id") int id){

            try{
                actrl.destroy(id);
                
                return id + " deleted";
            }
            catch(NonexistentEntityException error){
                return id + " tidak ada";
            }
    }
    
    
    
}
