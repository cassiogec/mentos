package br.com.servidor;

import br.com.DAO.VeiculoDAO;
import br.com.negocio.Veiculo;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Mentos
 */
@Path("veiculos")
public class VeiculosWS {

    @Context
    private UriInfo context;
    private VeiculoDAO veiculoDAO;

    /**
     * Creates a new instance of MentosWS
     */
    public VeiculosWS() {
    }

    @GET
    @Path("/get/veiculos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> consultarVeiculos() {
        
        veiculoDAO = new VeiculoDAO();
        return veiculoDAO.consultarVeiculos();
    }
    
    @GET
    @Path("/get/veiculostipo")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> consultarVeiculosTipo(String content) {
        
        veiculoDAO = new VeiculoDAO();
        return veiculoDAO.consultarVeiculosPorTipo(Integer.parseInt(content));
    }

    @GET
    @Path("/get/veiculo")
    @Produces(MediaType.APPLICATION_JSON)
    public Veiculo consultarVeiculo(int codigo) {
        
        veiculoDAO = new VeiculoDAO();
        return veiculoDAO.consultarVeiculo(codigo);
    }
   
    @GET
    @Path("/get/veiculoplaca")
    @Produces(MediaType.APPLICATION_JSON)
    public Veiculo consultarVeiculoPlaca(String placa) {
        
        veiculoDAO = new VeiculoDAO();
        return veiculoDAO.consultarVeiculo(placa);
    }
   
    @DELETE
    @Path("/delete/excluir")
    @Produces(MediaType.APPLICATION_JSON)
    public void excluirVeiculo(Veiculo veiculo) throws Exception{
        
        veiculoDAO = new VeiculoDAO();
        veiculoDAO.excluir(veiculo);
    }

    @PUT
    @Path("/put/alterarveiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alterarVeiculo(Veiculo veiculo) throws Exception{
        veiculoDAO = new VeiculoDAO();
        veiculoDAO.alterar(veiculo);
    }
    
    @POST
    @Path("/post/incluirveiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void incluirVeiculo(Veiculo veiculo) throws Exception{
        veiculoDAO = new VeiculoDAO();
        veiculoDAO.incluir(veiculo);
    }

    @POST
    @Path("/post/verificarplaca")
    @Consumes(MediaType.APPLICATION_JSON)
    public void verificarPlaca(String content) throws Exception{
        veiculoDAO = new VeiculoDAO();
        veiculoDAO.verificaplaca(content);
    }
    
    @POST
    @Path("/post/verificarcodigo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void verificarCodigo(int content) throws Exception{
        veiculoDAO = new VeiculoDAO();
        veiculoDAO.verificacodigo(content);
    }
       
    /**
     * PUT method for updating or creating an instance of VeiculosWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
