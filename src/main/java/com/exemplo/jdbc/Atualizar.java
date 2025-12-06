package com.exemplo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Atualizar {
    
    static void atualizarLiderProjeto(Connection conexao) throws SQLException {
        String sql = "update projeto set responsavel = ? where codigo = ?";
        
        try (PreparedStatement atuAtt = conexao.prepareStatement(sql)) {
            
            // novo responsável (cod 8), no projeto 3
            atuAtt.setInt(1, 8);
            atuAtt.setInt(2, 3);
            
            // atualiza
            int linhasAfetadas = atuAtt.executeUpdate();
            
            System.out.println("Responsável pelo projeto atualizado!");
            System.out.println("Projeto código: 3");
            System.out.println("Novo responsável código: 8");
            System.out.println("Linhas afetadas: " + linhasAfetadas + "\n");
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar responsável: " + e.getMessage());
            throw e;
        }
    }
}
