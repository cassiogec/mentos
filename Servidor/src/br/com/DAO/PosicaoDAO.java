/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.negocio.Posicao;
import br.com.util.HibernateUtil3;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.JDBCConnectionException;

/**
 *
 * @author leonardo.rocha
 */
public class PosicaoDAO {
    
    private boolean reaope;
    
    public PosicaoDAO() {
       
    }
    
    public Session retornaSession()
    {
	try
        {
            Session s = HibernateUtil3.getSessionFactory().openSession();
            s.createQuery("SELECT COUNT(codigo) FROM Veiculo").uniqueResult();
            reaope=true;
            return s;
        }
        catch (ExceptionInInitializerError ex) {
            Session s2 = HibernateUtil3.getSessionFactory2().openSession();
            reaope=false;
            return s2;
        } 
        catch (JDBCConnectionException ex2)
        {
            Session s2 = HibernateUtil3.getSessionFactory2().openSession();
            reaope=false;
            return s2;
        }
    }
    
    private boolean verificaPlaca(String placa)
    {
        Pattern p = Pattern.compile("[a-zA-Z]{3}[0-9]{4}");
        return p.matcher(placa).matches();
    }
    
    public boolean verificacodigo(int codigo) {
        Session s = retornaSession();
        Long u = (Long)s.createQuery("SELECT COUNT(codigo) FROM Veiculo WHERE codigo = :a")
                 .setInteger("a", codigo)
                 .setTimeout(30)
                 .uniqueResult();
        s.close();
        if (u > 0)
            return true;
        else
            return false;
    }
    
    public void validarParametros (Posicao posicao) throws Exception
    {
        if (posicao == null)
            throw new Exception("Objeto Posição 'NULL'");
        
        if (posicao.getVeiculo() == null)
            throw new Exception("Objeto Veículo 'NULL'");
        
        if (!reaope)
            throw new Exception("Não é Possível Realizar Operações no Banco Replicado");
        
        if (!verificaPlaca(posicao.getVeiculo().getPlaca()))
            throw new Exception("Placa do Carro Estar no Seguinte Formato: 'AAA9999'");
        
        if (!verificacodigo(posicao.getVeiculo().getCodigo()))
            throw new Exception("Veículo Informado Não Localizado"); 
    }
    
    public void incluir(Posicao posicao) throws Exception{ 
        Session s = retornaSession();
        validarParametros(posicao);
        
        //Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        posicao.getVeiculo().setPlaca(posicao.getVeiculo().getPlaca().toUpperCase());
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.save(posicao);
        trans.commit();
        s.close();
    }
    
    public void alterar(Posicao posicao) throws Exception{ 
        Session s = retornaSession();
        validarParametros(posicao);
        
        //Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        posicao.getVeiculo().setPlaca(posicao.getVeiculo().getPlaca().toUpperCase());
        
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.update(posicao);
        trans.commit();
        s.close();
    }
    
    public void excluir(Posicao posicao) throws Exception{ 
       // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        if (posicao == null)
            throw new Exception("Objeto Posição 'NULL'");
        if (posicao.getVeiculo() == null)
            throw new Exception("Objeto Veículo 'NULL'");
        if (!reaope)
            throw new Exception("Não é Possível Realizar Operações no Banco Replicado");

        Session s = retornaSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.delete(posicao);
        trans.commit();
        s.close();
    }
    
    public Posicao consultarPosicao(int codvei, int posicao){
        Session s = retornaSession();
        Posicao u =  (Posicao) s.createQuery("FROM Posicao WHERE codigo = :a AND veiculo.codigo = :b ORDER BY datahora")
                    .setInteger("a", posicao)
                    .setInteger("b", codvei)
                    .setTimeout(30)
                    .uniqueResult();
        s.close();
        return u;
    }
    
    public List<Posicao> consultarPosicao(int codvei, Calendar datIni) throws Exception{
        Session s = retornaSession();
        
        List<Posicao> u = s.createQuery("FROM Posicao WHERE datahora >= :a AND veiculo.codigo = :b ORDER BY datahora")
                    .setCalendar("a", datIni)
                    .setInteger("b", codvei)
                    .setTimeout(30)
                    .list();
        s.close();
        return u;
    }
    
    public List<Posicao> consultarPosicoesCarro(int codvei){
        Session s = retornaSession();
        List<Posicao> u =  s.createQuery("FROM Posicao WHERE veiculo.codigo = :a ORDER BY datahora")
                    .setInteger("a", codvei)
                    .setTimeout(30)
                    .list();
        s.close();
        return u;
    }
}
