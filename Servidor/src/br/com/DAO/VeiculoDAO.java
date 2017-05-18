/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.negocio.Veiculo;
import br.com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author leonardo.rocha
 */
public class VeiculoDAO {
    
    private Session s;
    
    public VeiculoDAO() {
        s = HibernateUtil.getSessionFactory().openSession();  
    }
    
    
    
    public boolean verificacodigo(int codigo) {
           Long u = (Long) s.createQuery("SELECT COUNT(codigo) FROM veiculo WHERE codigo = :a")
                    .setInteger("a", codigo)
                    .uniqueResult();
           if (u > 0)
               return true;
           else
               return false;
    }
    
    public boolean verificaplaca(String placa) {
            Long u = (Long) s.createQuery("SELECT COUNT(codigo) FROM veiculo WHERE placa = :a")
                    .setString("a", placa)
                    .uniqueResult();
           if (u == 0)
               return true;
           else
               return false;
    }
    
    // RETORNA FALSO QUANDO A PLACA JÁ ESTIVER CADASTRADA NO SISTEMA
    // PARA ADICIONAR UM VEÍCULO, PASSAR UM OBJETO SEM CÓDIGO
    public boolean incluir(Veiculo Veiculo) throws Exception{ 
        if (!verificaplaca(Veiculo.getPlaca()))
            return false;
       // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(10);
        s.save(Veiculo);
        trans.commit();
       // s.close();
        return true;
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public boolean alterar(Veiculo Veiculo) throws Exception{ 
        if (!verificacodigo(Veiculo.getCodigo()))
            return false;
       // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(10);
        s.update(Veiculo);
        trans.commit();
       // s.close();
        return true;
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public boolean excluir(Veiculo Veiculo) throws Exception{ 
        if (Veiculo == null)
            return false;
        if (!verificacodigo(Veiculo.getCodigo()))
            return false;
        Transaction trans = s.beginTransaction();
        trans.setTimeout(10);
        s.delete(Veiculo);
        trans.commit();
        //s.close();
        return true;
    }
    
    // RETORNA A LISTA DE TODOS OS VEÍCULOS
    public List<Veiculo> consultarVeiculos(){
        List<Veiculo> u =  s.createQuery("FROM veiculo")    
                    .list();
        return u;
    }
    
    // RETORNA A LISTA DOS VEÍCULOS DO TIPO SELECIONADO
    public List<Veiculo> consultarVeiculosPorTipo(int tipo){
        List<Veiculo> u = s.createQuery("FROM veiculo WHERE tipo = :a")
                    .setInteger("a", tipo)
                    .list();
        return u;
    }
    
    // RETORNA VEÍCULO ATRAVÉS DO CÓDIGO
    public Veiculo consultarVeiculo(int codvei){
        Veiculo u =  (Veiculo) s.createQuery("FROM veiculo WHERE codigo = :a")
                    .setInteger("a", codvei)
                    .uniqueResult();
        return u;
    }
    
    // RETORNA VEÍCULO POR PLACA ATRAVÉS DO CÓDIGO
    public Veiculo consultarVeiculo(String plavei){
        Veiculo u =  (Veiculo) s.createQuery("FROM veiculo WHERE placa = :a")
                    .setString("a", plavei)
                    .uniqueResult();
        return u;
    }
}
