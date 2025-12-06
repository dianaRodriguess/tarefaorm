package com.exemplo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import static com.exemplo.jdbc.Atualizar.atualizarLiderProjeto;
import static com.exemplo.jdbc.Inserir.inserirAtividade;
import static com.exemplo.jdbc.Listar.listarProjetosEAtividades;

public class ProgramaJDBC {
    
    public static void main(String[] args) {
        Connection conexao = null;
        
        try {
            conexao = ConexaoJDBC.getConexao();
            
            System.out.println("\n========================================");
            System.out.println("  OPERAÇÕES COM JDBC");
            System.out.println("========================================\n");
            
            // a. Inserir uma atividade em algum projeto;
            inserirAtividade(conexao);
            // b. Atualizar o líder de algum projeto;
            atualizarLiderProjeto(conexao);
            // c. Listar todos os projetos e suas atividades;
            listarProjetosEAtividades(conexao);
            
        } catch (SQLException e) {
            System.err.println("Erro ao executar operações: " + e.getMessage());
            e.getStackTrace();
        } finally {
            ConexaoJDBC.fecharConexao(conexao);
        }
    }
}