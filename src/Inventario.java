/**
 *
 * @author Havoc
 */
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import javax.ws.rs.core.MediaType;

public class Inventario {
private WebResource resource;
private ClientResponse response;
//private String URI="http://localhost:8080/Belfiori/Servicios/Inventario/"; //Prueba con localhost
private String URI="http://belfiori.azurewebsites.net/Belfiori/Servicios/Inventario/"; //Dirección del host en Azure
private String user,name,pass;
   
/****************************************** CONSTRUCTORES *********************************************************/
public Inventario(){
this.name=name;
this.pass=pass;
}

public Inventario(String servicio, String name, String pass, String id){  //Construye el objeto para consultar un solo producto
this.name=name;
this.pass=pass;
user="?user="+name+"&pass="+pass+"&id="+id;
Client c = Client.create();
resource = c.resource(URI+servicio+user); 
response = resource.type(MediaType.APPLICATION_XML).get(ClientResponse.class); 
}

public Inventario(String servicio, String name, String pass){     //Construye el objeto con la uri requerida para la consulta
this.name=name;
this.pass=pass;
user="?user="+name+"&pass="+pass;
Client c = Client.create();
resource = c.resource(URI+servicio+user); 
response = resource.type(MediaType.APPLICATION_XML).get(ClientResponse.class); 
}

/*********************************************** MÉTODOS *********************************************************/
public String[] get(){          //Este método procesa las datos obtenidos de la consulta según tipo de producto.
String cadena=null;
String[] parts=null;

cadena=resource.get(String.class);
cadena=cadena.replace("[","");
cadena=cadena.replace("]","");
parts = cadena.split(";, ");
if(response.getStatus()==200)
    {
    for(int i=0;i<parts.length;i++)
        {
        parts[i]=parts[i].replace(";","");     
        } 
    }
return parts;    
}

public int actualizar(List<String> id){     //Este método actualiza la BD restando los productos vendidos
Client c = Client.create();    
for(int i=0;i<id.size();i++)
    {
    resource = c.resource(URI+"Actualizar/"+name+"/"+pass+"/"+id.get(i));
    response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, "10");
    }
return response.getStatus();
}

}