package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDMySQL {
    public Connection bdmysql(){
        Connection conexao = null;

        try {
            String url = "jdbc:mysql://127.0.0.1/cadastro?user=root";
            conexao = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println("Problema com a conexão BD");
        }

        return conexao;
    }
}