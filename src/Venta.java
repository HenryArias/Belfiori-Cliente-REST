/**
 *
 * @author Havoc
 */
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;

public class Venta {
private List<String> id = new ArrayList<>();
private List<String> datos = new ArrayList<>();
private WebResource resource;
private ClientResponse response;
private Client c;
//private String URI="http://localhost:8080/Belfiori/Servicios/Venta/"; //Prueba con localhost
private String URI="http://belfiori.azurewebsites.net/Belfiori/Servicios/Venta/"; //Dirección host en Azure
private String user,name,pass;

/****************************************** CONSTRUCTORES *********************************************************/
public Venta(){
    
}   

public Venta(List<String> datos, List<String> id, String name, String pass){

this.datos=datos;
this.id=id;
this.name=name;
this.pass=pass;
user="?user="+name+"&pass="+pass;
c = Client.create();   
}

/*********************************************** MÉTODOS *********************************************************/
private int factura(){      //Método para consultar el número de facturas en la BD
int salida=0;

resource = c.resource(URI+"NoFactura"+user); 
response = resource.type(MediaType.APPLICATION_XML).get(ClientResponse.class);
salida=Integer.parseInt(resource.get(String.class));
return salida;    
}

public int registrar(){  //Este método registra la venta en la BD
int aux=0;      

aux=factura()+1;
resource = c.resource(URI+"Registrar/"+name+"/"+pass+"/"+aux+"/"+datos.get(0)+"/"+datos.get(1)+"/"+datos.get(2)+"/"+datos.get(3)+"/"+datos.get(4)+"/"+datos.get(5)+"/"+datos.get(6));
response = resource.type(MediaType.TEXT_PLAIN).post(ClientResponse.class, "10");
for(int i=0;i<id.size();i++)        //Registra los productos vendidos según la factura que se este procesando
    {
    resource = c.resource(URI+"Compone/"+name+"/"+pass+"/"+aux+"/"+id.get(i)); 
    response = resource.type(MediaType.TEXT_PLAIN).post(ClientResponse.class, "10");
    }
return response.getStatus();
}

}
