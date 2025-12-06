package com.exemplo.jdbc;

import java.sql.*;

public class Listar {
    
    static void listarProjetosEAtividades(Connection conexao) throws SQLException {
        String sql = "select pr.nome as Projeto, " +
                "       coalesce(a.descricao, 'Sem Atividades') as Atividade, " +
                "       a.data_inicio as Inicio, " +
                "       a.data_fim as Fim " +
                "from projeto pr " +
                "left join atividade a on pr.codigo = a.projeto " +
                "order by pr.nome";
        
        try (Statement lisProAtt = conexao.createStatement();
             ResultSet rs = lisProAtt.executeQuery(sql)) {
            
            // cabeçalho
            System.out.println("Projetos e Atividades:");
            System.out.println("─".repeat(100));
            System.out.printf("%-20s | %-30s | %-12s | %-12s%n",
                    "PROJETO", "ATIVIDADE", "INÍCIO", "FIM");
            System.out.println("─".repeat(100));
            
            int contador = 0;
            // percorre as tabelas
            while (rs.next()) {
                String projeto = rs.getString("Projeto");
                String atividade = rs.getString("Atividade");
                Date inicio = rs.getDate("Inicio");
                Date fim = rs.getDate("Fim");
                
                // formatação
                System.out.printf("%-20s | %-30s | %-12s | %-12s%n",
                        projeto,
                        atividade,
                        inicio != null ? inicio.toString() : "---",
                        fim != null ? fim.toString() : "---");
                contador++;
            }
            
            System.out.println("─".repeat(100));
            System.out.println("Total de registros: " + contador + "\n");
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar projetos: " + e.getMessage());
            throw e;
        }
    }
}
