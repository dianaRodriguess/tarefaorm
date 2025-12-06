package com.exemplo.orm;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import static com.exemplo.orm.Atualizar.atualizarLiderProjeto;
import static com.exemplo.orm.Inserir.inserirAtividade;
import static com.exemplo.orm.Listar.listarProjetosEAtividades;

public class ProgramaORM {
    
    private static EntityManagerFactory emf;
    
    public static void main(String[] args) {
        
        try {
            // cria a fantástica fábrica de conexões
            // por ser pesado, é bom ser criada só uma vez e reutilizada
            emf = Persistence.createEntityManagerFactory("AtividadesPU");
            System.out.println("✓ EntityManagerFactory criado com sucesso!\n");
            
            System.out.println("========================================");
            System.out.println("  OPERAÇÕES COM HIBERNATE)");
            System.out.println("========================================\n");
            
            // a. Inserir uma atividade em algum projeto;
            inserirAtividade(emf);
            // b. Atualizar o líder de algum projeto;
            atualizarLiderProjeto(emf);
            // c. Listar todos os projetos e suas atividades;
            listarProjetosEAtividades(emf);
            
        } catch (Exception e) {
            System.err.println("Erro ao executar operações: " + e.getMessage());
            e.getStackTrace();
        } finally {
            // sempre fechar a fábrica
            if (emf != null && emf.isOpen()) {
                emf.close();
                System.out.println("\nEntityManagerFactory fechado!");
            }
        }
    }
}
