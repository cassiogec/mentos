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
    
    private boolean verificaStringCarro(Veiculo veiculo)
    {
        if (veiculo.getPlaca().length() != 7)
            return false;
        if (veiculo.getUncapac().length() != 5)
            return false;
        return true;
    }
    
    public boolean verificacodigo(int codigo) {
           Long u = (Long) s.createQuery("SELECT COUNT(codigo) FROM Veiculo WHERE codigo = :a")
                    .setInteger("a", codigo)
                    .setTimeout(30)
                    .uniqueResult();
           if (u > 0)
               return true;
           else
               return false;
    }
    
    public boolean verificaplaca(String placa) {
            Long u = (Long) s.createQuery("SELECT COUNT(codigo) FROM Veiculo WHERE placa = :a")
                    .setString("a", placa)
                    .setTimeout(30)
                    .uniqueResult();
           if (u == 0)
               return true;
           else
               return false;
    }
    
    // RETORNA FALSO QUANDO A PLACA JÁ ESTIVER CADASTRADA NO SISTEMA
    // PARA ADICIONAR UM VEÍCULO, PASSAR UM OBJETO SEM CÓDIGO
    public void incluir(Veiculo Veiculo) throws Exception{ 
        if (!verificaplaca(Veiculo.getPlaca()))
            throw new Exception("Placa Já Cadastrada");
        if (!verificaStringCarro(Veiculo))
            throw new Exception("Placa do Carro Deve Possuir Exatamente 7 Caracteres e o Campo 'UnCapac' Deve Possuir Exatamente 5 Caracteres");
       // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.save(Veiculo);
        trans.commit();
       // s.close();
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public void alterar(Veiculo Veiculo) throws Exception{ 
        if (!verificacodigo(Veiculo.getCodigo()))
            throw new Exception("Placa Já Cadastrada");
        if (!verificaStringCarro(Veiculo))
            throw new Exception("Placa do Carro Deve Possuir Exatamente 7 Caracteres e o Campo 'UnCapac' Deve Possuir Exatamente 5 Caracteres");
       // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.update(Veiculo);
        trans.commit();
       // s.close();
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public void excluir(Veiculo Veiculo) throws Exception{ 
        if (Veiculo == null)
            throw new Exception("Objeto Veículo 'NULL'");
        if (!verificacodigo(Veiculo.getCodigo()))
            throw new Exception("Veículo Não Localizado");
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.delete(Veiculo);
        trans.commit();
        //s.close();
    }
    
    // RETORNA A LISTA DE TODOS OS VEÍCULOS
    public List<Veiculo> consultarVeiculos(){
        List<Veiculo> u =  s.createQuery("FROM Veiculo") 
                    .setTimeout(30)
                    .list();
        return u;
    }
    
    // RETORNA A LISTA DOS VEÍCULOS DO TIPO SELECIONADO
    public List<Veiculo> consultarVeiculosPorTipo(int tipo){
        List<Veiculo> u = s.createQuery("FROM Veiculo WHERE tipo = :a")
                    .setInteger("a", tipo)
                    .setTimeout(30)
                    .list();
        return u;
    }
    
    // RETORNA VEÍCULO ATRAVÉS DO CÓDIGO
    public Veiculo consultarVeiculo(int codvei){
        Veiculo u =  (Veiculo) s.createQuery("FROM Veiculo WHERE codigo = :a")
                    .setInteger("a", codvei)
                    .setTimeout(30)
                    .uniqueResult();
        return u;
    }
    
    // RETORNA VEÍCULO POR PLACA ATRAVÉS DO CÓDIGO
    public Veiculo consultarVeiculo(String plavei){
        Veiculo u =  (Veiculo) s.createQuery("FROM Veiculo WHERE placa = :a")
                    .setString("a", plavei)
                    .setTimeout(30)
                    .uniqueResult();
        return u;
    }
}
