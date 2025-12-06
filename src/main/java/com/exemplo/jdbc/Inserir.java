package com.exemplo.jdbc;

import java.sql.*;

public class Inserir {
    
    static void inserirAtividade(Connection conexao) throws SQLException {
        String sql = "insert into atividade (descricao, projeto, data_inicio, data_fim) " +
                "values (?, ?, ?, ?)";
        
        try (PreparedStatement insAtt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            // parâmetros do insert
            insAtt.setString(1, "TAS - Atividade 13");
            insAtt.setInt(2, 4);
            insAtt.setDate(3, Date.valueOf("2017-11-10"));
            insAtt.setDate(4, Date.valueOf("2019-08-19"));
            
            // executa
            int linhasAfetadas = insAtt.executeUpdate();
            
            // pega o id
            ResultSet rs = insAtt.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                System.out.println("Atividade inserida com sucesso!");
                System.out.println("ID gerado: " + idGerado);
                System.out.println("Descrição: TAS - Atividade 13");
                System.out.println("Linhas afetadas: " + linhasAfetadas + "\n");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir atividade: " + e.getMessage());
            throw e;
        }
    }
}
