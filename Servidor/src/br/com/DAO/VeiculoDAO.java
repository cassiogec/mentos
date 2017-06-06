/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;

import br.com.negocio.Veiculo;
import br.com.util.HibernateUtil3;
import java.util.List;
import java.util.regex.Pattern;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author leonardo.rocha
 */
public class VeiculoDAO {
      
    private boolean reaope;
    
    public VeiculoDAO() {
    }
    
    public Session retornaSession()
    {
        
        try
        {
            Session s = HibernateUtil3.getSessionFactory().openSession();
            s.createQuery("SELECT COUNT(codigo) FROM Veiculo");
            reaope=true;
            return s;
        }
        catch (ExceptionInInitializerError ex) {
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
    
    private void verificaStringCarro(Veiculo veiculo) throws Exception
    {
        if (veiculo.getPlaca().length() != 7)
            throw new Exception("Placa do Carro Deve Possuir Exatamente 7 Caracteres");
        if (veiculo.getUncapac().length() != 5)
            throw new Exception("Campo Unidade Deve Possuir Exatamente 5 Caracteres");
    }
    
    public boolean verificacodigo(int codigo) {
        Session s = retornaSession();
        Long u = (Long) s.createQuery("SELECT COUNT(codigo) FROM Veiculo WHERE codigo = :a")
                 .setInteger("a", codigo)
                 .setTimeout(30)
                 .uniqueResult();
        s.close();
        if (u > 0)
            return true;
        else
            return false;
    }
    
    public void validarParametros(Veiculo veiculo) throws Exception
    {
        verificaStringCarro(veiculo);
        
        if (!reaope)
            throw new Exception("Não é Possível Realizar Operações no Banco Replicado");
        if (!verificaPlaca(veiculo.getPlaca()))
            throw new Exception("Placa do Carro Estar no Seguinte Formato: 'AAA9999'");
        if (veiculo.getTipo() < 1 || veiculo.getTipo() > 8)
            throw new Exception("Tipo do Veículo Deve Ser Entre 1 e 8!");
    }
    
    public boolean verificaplaca(String placa) {
        placa = placa.toUpperCase();
        
        Session s = retornaSession();
        Long u = (Long) s.createQuery("SELECT COUNT(codigo) FROM Veiculo WHERE placa = :a")
                .setString("a", placa)
                .setTimeout(30)
                .uniqueResult();
        s.close();
       if (u == 0)
           return true;
       else
           return false;
    }
    
    public void incluir(Veiculo veiculo) throws Exception{ 
        // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        
        Session s = retornaSession();
        validarParametros(veiculo);
        
        if (!verificaplaca(veiculo.getPlaca()))
            throw new Exception("Placa Já Cadastrada");
        
        veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.save(veiculo);
        trans.commit();
        s.close();
    }
    
    public void alterar(Veiculo veiculo) throws Exception{ 
        
        // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        
        Session s = retornaSession();
        validarParametros(veiculo);
        veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.update(veiculo);
        trans.commit();
        s.close();
    }
    
    public void excluir(Veiculo Veiculo) throws Exception{ 
        if (Veiculo == null)
            throw new Exception("Objeto Veículo 'NULL'");
        if (!verificacodigo(Veiculo.getCodigo()))
            throw new Exception("Veículo Não Localizado");
        if (!reaope)
            throw new Exception("Não é Possível Realizar Operações no Banco Replicado");
        Session s = retornaSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.delete(Veiculo);
        trans.commit();
        s.close();
    }
    
    public List<Veiculo> consultarVeiculos(){
        Session s = retornaSession();
        List<Veiculo> u =  s.createQuery("FROM Veiculo") 
                    .setTimeout(30)
                    .list();
        s.close();
        return u;
    }

    public List<Veiculo> consultarVeiculosPorTipo(int tipo){
        Session s = retornaSession();
        List<Veiculo> u = s.createQuery("FROM Veiculo WHERE tipo = :a")
                    .setInteger("a", tipo)
                    .setTimeout(30)
                    .list();
        s.close();
        return u;
    }
    
    public Veiculo consultarVeiculo(int codvei){
        Session s = retornaSession();
        Veiculo u =  (Veiculo) s.createQuery("FROM Veiculo WHERE codigo = :a")
                    .setInteger("a", codvei)
                    .setTimeout(30)
                    .uniqueResult();
        s.close();
        return u;
    }
    
    public Veiculo consultarVeiculo(String plavei) throws Exception{
        plavei = plavei.toUpperCase();
        
        Session s = retornaSession();
        Veiculo u =  (Veiculo) s.createQuery("FROM Veiculo WHERE placa = :a")
                    .setString("a", plavei)
                    .setTimeout(30)
                    .uniqueResult();
        if (u == null)
            throw new Exception("Veículo não encontrado!");
        if (!verificaPlaca(plavei))
            throw new Exception("Placa do Carro Estar no Seguinte Formato: 'AAA9999'");
        s.close();
        return u;
    }
}
