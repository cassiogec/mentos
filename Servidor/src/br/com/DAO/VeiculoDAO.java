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
            System.out.println("BANCO ORIGINAL");
            reaope=true;
            return s;
        }
        catch (ExceptionInInitializerError ex) {
                Session s2 = HibernateUtil3.getSessionFactory2().openSession();
                System.out.println("BANCO REPLICADO");
                reaope=false;
                return s2;
        } 
    }
    
    private boolean verificaPlaca(String placa)
    {
        Pattern p = Pattern.compile("[a-zA-Z]{3}[0-9]{4}");
        return p.matcher(placa).matches();
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
    
    public boolean verificaplaca(String placa) {
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
    
    // RETORNA FALSO QUANDO A PLACA JÁ ESTIVER CADASTRADA NO SISTEMA
    // PARA ADICIONAR UM VEÍCULO, PASSAR UM OBJETO SEM CÓDIGO
    public void incluir(Veiculo Veiculo) throws Exception{ 
        if (!verificaplaca(Veiculo.getPlaca()))
            throw new Exception("Placa Já Cadastrada");
        if (!verificaStringCarro(Veiculo))
            throw new Exception("Placa do Carro Deve Possuir Exatamente 7 Caracteres e o Campo 'UnCapac' Deve Possuir Exatamente 5 Caracteres");
        if (!reaope)
            throw new Exception("Não é Possível Realizar Operações no Banco Replicado");
        if (!verificaPlaca(Veiculo.getPlaca()))
            throw new Exception("Placa do Carro Estar no Seguinte Formato: 'AAA9999'");
        if (Veiculo.getTipo() < 1 || Veiculo.getTipo() > 8)
            throw new Exception("Tipo do Veículo Deve Ser Entre 1 e 8!");
        // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Session s = retornaSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.save(Veiculo);
        trans.commit();
        s.close();
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public void alterar(Veiculo Veiculo) throws Exception{ 
        if (!verificacodigo(Veiculo.getCodigo()))
            throw new Exception("Placa Já Cadastrada");
        if (!verificaStringCarro(Veiculo))
            throw new Exception("Placa do Carro Deve Possuir Exatamente 7 Caracteres e o Campo 'UnCapac' Deve Possuir Exatamente 5 Caracteres");
        if (!reaope)
            throw new Exception("Não é Possível Realizar Operações no Banco Replicado");
        if (!verificaPlaca(Veiculo.getPlaca()))
            throw new Exception("Placa do Carro Estar no Seguinte Formato: 'AAA9999'");
        if (Veiculo.getTipo() < 1 || Veiculo.getTipo() > 8)
            throw new Exception("Tipo do Veículo Deve Ser Entre 1 e 8!");
        // Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Session s = retornaSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.update(Veiculo);
        trans.commit();
        s.close();
    }
    
    // RETORNA FALSO SE O CÓDIGO DO VEÍCULO NÃO EXISTIR
    public void excluir(Veiculo Veiculo) throws Exception{ 
        if (Veiculo == null)
            throw new Exception("Objeto Veículo 'NULL'");
        if (!verificacodigo(Veiculo.getCodigo()))
            throw new Exception("Veículo Não Localizado");
        if (!reaope)
            throw new Exception("Não é Possível Realizar Operações no Banco Replicado");
        if (!verificaPlaca(Veiculo.getPlaca()))
            throw new Exception("Placa do Carro Estar no Seguinte Formato: 'AAA9999'");
        Session s = retornaSession();
        Transaction trans = s.beginTransaction();
        trans.setTimeout(30);
        s.delete(Veiculo);
        trans.commit();
        s.close();
    }
    
    // RETORNA A LISTA DE TODOS OS VEÍCULOS
    public List<Veiculo> consultarVeiculos(){
        Session s = retornaSession();
        List<Veiculo> u =  s.createQuery("FROM Veiculo") 
                    .setTimeout(30)
                    .list();
        s.close();
        return u;
    }
    
    // RETORNA A LISTA DOS VEÍCULOS DO TIPO SELECIONADO
    public List<Veiculo> consultarVeiculosPorTipo(int tipo){
        Session s = retornaSession();
        List<Veiculo> u = s.createQuery("FROM Veiculo WHERE tipo = :a")
                    .setInteger("a", tipo)
                    .setTimeout(30)
                    .list();
        s.close();
        return u;
    }
    
    // RETORNA VEÍCULO ATRAVÉS DO CÓDIGO
    public Veiculo consultarVeiculo(int codvei){
        Session s = retornaSession();
        Veiculo u =  (Veiculo) s.createQuery("FROM Veiculo WHERE codigo = :a")
                    .setInteger("a", codvei)
                    .setTimeout(30)
                    .uniqueResult();
        s.close();
        return u;
    }
    
    // RETORNA VEÍCULO POR PLACA ATRAVÉS DO CÓDIGO
    public Veiculo consultarVeiculo(String plavei) throws Exception{
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
