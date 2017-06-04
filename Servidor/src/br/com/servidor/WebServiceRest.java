package br.com.servidor;

import br.com.DAO.PosicaoDAO;
import br.com.DAO.VeiculoDAO;
import br.com.negocio.Posicao;
import br.com.negocio.Veiculo;
import br.com.util.Logger;
//import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author RastreaMentos
 */
@Path("mentos")
public class WebServiceRest {

    @Context
    private UriInfo context;

    public WebServiceRest() {
    }

    @POST
    @Path("/post/incluir-veiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean adicionarVeiculo(RequestBodyREST requestBodyREST) throws Exception {
        try {
            Veiculo v = new Veiculo(requestBodyREST.dsPlaca, 
                    requestBodyREST.idTipo,
                    requestBodyREST.vlCapacidade,
                    requestBodyREST.dsUnidade);
            VeiculoDAO vdao = new VeiculoDAO();
            vdao.incluir(v);
            Logger.logMethod("Rest", "");
            return true;
        } catch (SQLException e) { 
            Logger.logMethod("Rest", e.getMessage());
            throw new Exception("Não foi possivel inserir o veículo.");
        } catch (Exception ex) {
            Logger.logMethod("Rest", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    @PUT
    @Path("/put/alterar-veiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean alterarVeiculo(RequestBodyREST requestBodyREST) throws Exception {
        try {
            Veiculo v = new Veiculo(requestBodyREST.cdVeiculo,
                    requestBodyREST.dsPlaca,
                    requestBodyREST.idTipo,
                    requestBodyREST.vlCapacidade,
                    requestBodyREST.dsUnidade);
            VeiculoDAO vdao = new VeiculoDAO();
            vdao.alterar(v);
            Logger.logMethod("Rest", "");
            return true;
        } catch (SQLException e) {
            Logger.logMethod("Rest", e.getMessage());
            throw new Exception("Não foi possivel alterar o veículo.");
        } catch (Exception ex) {
            Logger.logMethod("Rest", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    /*
    * Método @DELETE não funcionou, há um bug na versão do java, que na versão 1.8(b98)
    * estaria corrigido, porém, não conseguimos usa-lo.
    * Link: http://bugs.java.com/view_bug.do?bug_id=7157360
    */
    @POST
    @Path("/delete/excluir-veiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean excluirVeiculo(RequestBodyREST requestBodyREST) throws Exception{
        try {
            Veiculo v = new Veiculo(requestBodyREST.cdVeiculo);
            VeiculoDAO vdao = new VeiculoDAO();
            vdao.excluir(v);
            Logger.logMethod("Rest", "");
            return true;
        }  catch (SQLException e) {
            Logger.logMethod("Rest", e.getMessage());
            throw new Exception("Não foi possivel excluir o veículo.");
        } catch (Exception ex) {
            Logger.logMethod("Rest", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    @GET
    @Path("/get/consultar-veiculos/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> consultarVeiculos(RequestBodyREST requestBodyREST) throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            List<Veiculo> list = vdao.consultarVeiculos();

            for (Veiculo v : list) {
                v.setPosicoes(null);
            }
            
            Logger.logMethod("Rest", "");
            return list;
        } catch (Exception ex) {
            Logger.logMethod("Rest", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    @GET
    @Path("/get/consultar-veiculo/{cdVeiculo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Veiculo consultarVeiculo(@PathParam("cdVeiculo")  Integer cdVeiculo) throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            Veiculo veiculo = vdao.consultarVeiculo(cdVeiculo);
            veiculo.setPosicoes(null);
            Logger.logMethod("Rest", "");
            return veiculo;
        } catch (Exception ex) {
            Logger.logMethod("Rest", ex.getMessage());
            throw new Exception("Não foi possivel consultar o veículo");
        }
    }
    
    @GET
    @Path("/get/listar-tipo/{idTipo}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> listaTipo(@PathParam("idTipo")  Integer idTipo) throws Exception{
        try {
            VeiculoDAO vdao = new VeiculoDAO();
            List<Veiculo> list = vdao.consultarVeiculosPorTipo(idTipo);

            for (Veiculo v : list) {
                v.setPosicoes(null);
            }
            Logger.logMethod("Rest", "");
            return list;
        } catch (Exception ex) {
            Logger.logMethod("Rest", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    @POST
    @Path("/post/localizacao")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Posicao> localizacao(RequestBodyREST requestBodyREST ) throws Exception {
        try {
            PosicaoDAO pdao = new PosicaoDAO();
            List<Posicao> list = new ArrayList<Posicao>();
            
            if (requestBodyREST.dtLocalizacao == null){
                list = pdao.consultarPosicoesCarro(requestBodyREST.cdVeiculo);
            } else {
                list = pdao.consultarPosicao(requestBodyREST.cdVeiculo,
                        requestBodyREST.dtLocalizacao);
            }
            
            Logger.logMethod("Rest", "");
            return list;
            
        } catch (Exception ex) {
            Logger.logMethod("Rest", ex.getMessage());
            throw new Exception(ex.getMessage());
       } 
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
