package br.com.servidor;

import br.com.DAO.PosicaoDAO;
import br.com.negocio.Posicao;
import java.util.Calendar;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Mentos LTDA.
 */
@Path("posicao")
public class PosicaoWS {

    @Context
    private UriInfo context;
    private PosicaoDAO posicaoDAO;

    /**
     * Creates a new instance of PosicaoWS
     */
    public PosicaoWS() {
    }

    @POST
    @Path("/post/consultaposicoescarro")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Posicao> consultarPosicoesCarro(int codigo) {
        posicaoDAO = new PosicaoDAO();
        return posicaoDAO.consultarPosicoesCarro(codigo);
    }

    @POST
    @Path("/post/consultaposicaocarro")
    @Consumes(MediaType.APPLICATION_JSON)
    public Posicao consultarPosicaoData(int codigo, Calendar dahpos){
        posicaoDAO = new PosicaoDAO();
        return posicaoDAO.consultarPosicao(codigo, dahpos);
    }
    
    @POST
    @Path("/post/consultaposicaocarro")
    @Consumes(MediaType.APPLICATION_JSON)
    public Posicao consultarPosicaoCodigo(int codigo, int posicao){
        posicaoDAO = new PosicaoDAO();
        return posicaoDAO.consultarPosicao(codigo, posicao);
    }
    
	@POST
    @Path("/post/incluir")
    @Consumes(MediaType.APPLICATION_JSON)
    public void incluirVeiculo(Posicao posicao) throws Exception{
        posicaoDAO = new PosicaoDAO();
        posicaoDAO.incluir(posicao);
    }
	
    @DELETE
    @Path("/delete/excluir")
    @Consumes(MediaType.APPLICATION_JSON)
    public void excluirVeiculo(Posicao posicao) throws Exception{
        posicaoDAO = new PosicaoDAO();
        posicaoDAO.excluir(posicao);
    }
    
    @PUT
    @Path("/put/alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alterarVeiculo(Posicao posicao) throws Exception{
        posicaoDAO = new PosicaoDAO();
        posicaoDAO.alterar(posicao);
    }
    
    /**
     * PUT method for updating or creating an instance of PosicaoWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
