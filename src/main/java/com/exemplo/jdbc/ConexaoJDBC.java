package com.exemplo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBC {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/atividade_db";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";
    
    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão feita com sucesso!");
            return conexao;
            
        } catch (ClassNotFoundException e) {
            System.err.println("Não foi possível encontrar o driver!");
            throw new SQLException("Driver não encontrado", e);
        }
    }
    
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}