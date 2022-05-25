package bd.daos;

import bd.BDMySQL;
import bd.dbos.CadastroDbo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CadastroDaos {
    static Connection conection;
    static PreparedStatement ppstt;

    //Metodo para verificar se o CPF ja esta cadastrado
    public static boolean cadastrado(String cpf) throws Exception {

        //variavel para guardar o resultado
        String teste = "";

        conection = new BDMySQL().bdmysql();

        try {
            String mySql;

            mySql = "SELECT Cpf " +
                    "FROM cliente " +
                    "WHERE Cpf = " + cpf;
            ppstt = conection.prepareStatement(mySql);

            PreparedStatement statement = (PreparedStatement) conection.prepareStatement(mySql);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {

                teste = resultado.getNString("Cpf");

            }

            //verificando se a variavel esta vazia, pois se ela estiver o CPF nao existe no banco
            if (teste == null || teste.isEmpty())
                return false;


        } catch (SQLException erro) {
            throw new Exception("Erro ao procurar Cpf");
        }
        //retornando true pois se chegar a essa parte do codigo sifnifica que existe o cpf.
        return true;

    }

    //Metodo para incluir novo cadastro
    public static void incluir(CadastroDbo cadastro) throws Exception {

        conection = new BDMySQL().bdmysql();
        if (cadastro == null)
            throw new Exception("Informaçoes nao fornecidas ");

        //verificando se o cpf ja foi cadastrado, pois o Cpf é primary key no banco, e nao podem existir 2 iguais
        if (cadastrado(cadastro.getCpf()))
            throw new Exception("Cpf ja cadastrado");



        try {
            String mySql = "insert into cliente (Cpf,nome,CEP,Complemento,Numero_Casa) values (?,?,?,?,?)";
            ppstt = conection.prepareStatement(mySql);


            ppstt.setString(1, cadastro.getCpf());
            ppstt.setString(2, cadastro.getNome());
            ppstt.setString(3, cadastro.getCep());
            ppstt.setString(4, cadastro.getComplemento());
            ppstt.setInt(5, cadastro.getNumeroCasa());
            ppstt.execute();
            System.out.println("cadastrado com sucesso!");

        } catch (SQLException erro) {
            ppstt.close();
            throw new Exception("Erro ao cadastrar");
        }
    }

    //Metodo para excluir cadastro
    public static void excluir(String cpf) throws Exception {

        if (cadastrado(cpf) == false)
            throw new Exception("Cpf nao cadastrado");


        conection = new BDMySQL().bdmysql();
        try {
            String mySql;

            mySql = "DELETE FROM cliente WHERE Cpf=?";
            ppstt = conection.prepareStatement(mySql);


            ppstt.setString(1, cpf);

            ppstt.executeUpdate();
            ppstt.execute();

            System.out.println("Exclusao excutada com suecesso");
        } catch (SQLException erro) {
            conection.rollback();
            throw new Exception("Erro ao excluir o cadastro");
        }
    }

    //Metodo para alterar nome
    public static void alterarNome(String nome, String cpf) throws Exception {
        conection = new BDMySQL().bdmysql();

        if (cadastrado(cpf) == false)
            throw new Exception("cpf inesistente");

        if (nome == null || nome.isEmpty())
            throw new Exception("Nome nao fornecido");

        try {
            String mySql;

            mySql = "UPDATE cliente SET Nome=?  WHERE Cpf =?";

            ppstt = conection.prepareStatement(mySql);

            ppstt.setString(1, nome);
            ppstt.setString(2, cpf);


            ppstt.executeUpdate();
            ppstt.execute();
            System.out.println("Atualizaçao executada com sucesso!!");
        } catch (SQLException erro) {
            conection.rollback();
            throw new Exception("Erro ao atualizar dados");
        }
    }

    //Metodo para alterar o Endereço
    public static void alterarEndereço(String cep, String complemento,int numeroCasa, String cpf) throws Exception {
        conection = new BDMySQL().bdmysql();

        if (cadastrado(cpf) == false)
            throw new Exception("cpf inesistente");

        if (cep == null || cep.isEmpty())
            throw new Exception("Cep nao fornecido!!");


        if (numeroCasa <= 0)
            throw new Exception ("Numero invalido!!");


        try {
            String mySql;

            mySql = "UPDATE cliente SET CEP=?, Complemento=?, Numero_Casa=?  WHERE Cpf =?";

            ppstt = conection.prepareStatement(mySql);

            ppstt.setString(1, cep);
            ppstt.setString(2, complemento);
            ppstt.setInt   (3, numeroCasa);
            ppstt.setString(4, cpf);

            ppstt.executeUpdate();
            ppstt.execute();

            System.out.println("Atualizaçao executada com sucesso!!");
        }
        catch (SQLException erro) {
            conection.rollback();
            throw new Exception("Erro ao atualizar dados");
        }

    }

    //Metodo para alterar o cpf
    public static void alterarCpf(String cpf,String novoCpf) throws Exception {
        conection = new BDMySQL().bdmysql();

        if (cadastrado(cpf) == false)
            throw new Exception("cpf inesistente");

        //verificando se o cpf ja foi cadastrado, pois o Cpf é primary key no banco, e nao podem existir 2 iguais
        if (cadastrado(novoCpf) )
            throw new Exception("Cpf ja cadastrado");


        if (novoCpf==null || novoCpf.isEmpty())
            throw new Exception ("CPF invalido!!");


        try {
            String mySql;

            mySql = "UPDATE cliente SET Cpf=?  WHERE Cpf =?";

            ppstt = conection.prepareStatement(mySql);

            ppstt.setString(1, novoCpf);
            ppstt.setString(2, cpf);

            ppstt.executeUpdate();
            ppstt.execute();
            System.out.println("Atualizaçao executada com sucesso!!");
        }
        catch (SQLException erro) {

            conection.rollback();
            throw new Exception("Erro ao atualizar dados");
        }
    }

    //Metodo para retornar as informaçoes de cadastro
    public static ArrayList<CadastroDbo> getCadastro(String Cpf) throws Exception {
        if (cadastrado(Cpf) == false) {
            throw new Exception("Cpf nao cadastrado");
        }

        ArrayList<CadastroDbo> clientes = new ArrayList<CadastroDbo>();
        conection = new BDMySQL().bdmysql();
        try {
            String mySql;

            mySql = "SELECT * " +
                    "FROM cliente " +
                    "WHERE Cpf = " + Cpf;
            ppstt = conection.prepareStatement(mySql);

            PreparedStatement statement = (PreparedStatement) conection.prepareStatement(mySql);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                CadastroDbo cliente = new CadastroDbo();
                cliente.setCpf(resultado.getNString("Cpf"));
                cliente.setNome(resultado.getNString("Nome"));
                cliente.setNumeroCasa(resultado.getInt("Numero_Casa"));
                cliente.setComplemento(resultado.getNString("Complemento"));
                cliente.setCep(resultado.getNString("Cep"));
                clientes.add(cliente);
            }


        } catch (SQLException erro) {
            throw new Exception("Erro ao procurar Cpf");
        }
        return clientes;

    }

    public static String getCep(String Cpf) throws Exception {

        if (cadastrado(Cpf) == false)
            throw new Exception("Cpf nao cadastrado");


        String retornoValorFinal = "";
        conection = new BDMySQL().bdmysql();
        try {
            String mySql;

            mySql = "SELECT CEP " +
                    "FROM cliente " +
                    "WHERE Cpf = " + Cpf;
            ppstt = conection.prepareStatement(mySql);

            PreparedStatement statement = (PreparedStatement) conection.prepareStatement(mySql);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {

                retornoValorFinal = resultado.getNString("Cep");

            }


        } catch (SQLException erro) {
            throw new Exception("Erro ao procurar Cpf");
        }

        return retornoValorFinal;
    }




}
