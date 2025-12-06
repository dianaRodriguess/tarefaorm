package com.exemplo.orm;

import com.exemplo.orm.entidades.Atividade;
import com.exemplo.orm.entidades.Projeto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.text.SimpleDateFormat;
import java.util.List;

public class Listar {
    
    private static EntityManagerFactory emf;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    static void listarProjetosEAtividades(EntityManagerFactory emf) {
        
        try (EntityManager em = emf.createEntityManager()) {
            String queryString = "SELECT p FROM Projeto p ORDER BY p.nome";
            TypedQuery<Projeto> query = em.createQuery(queryString, Projeto.class);
            List<Projeto> projetos = query.getResultList();
            
            // cabeçalho
            System.out.println("Projetos e Atividades:");
            System.out.println("─".repeat(100));
            System.out.printf("%-20s | %-30s | %-12s | %-12s%n",
                    "PROJETO", "ATIVIDADE", "INÍCIO", "FIM");
            System.out.println("─".repeat(100));
            
            int totalRegistros = 0;
            
            // percorre tabelas
            for (Projeto projeto : projetos) {
                List<Atividade> atividades = projeto.getAtividades();
                
                if (atividades == null || atividades.isEmpty()) {
                    // projeto sem atividades
                    System.out.printf("%-20s | %-30s | %-12s | %-12s%n",
                            projeto.getNome(),
                            "Sem Atividades",
                            "---",
                            "---");
                    totalRegistros++;
                } else {
                    // projeto com atividades
                    for (Atividade atividade : atividades) {
                        System.out.printf("%-20s | %-30s | %-12s | %-12s%n",
                                projeto.getNome(),
                                atividade.getDescricao(),
                                atividade.getDataInicio() != null ?
                                        sdf.format(atividade.getDataInicio()) : "---",
                                atividade.getDataFim() != null ?
                                        sdf.format(atividade.getDataFim()) : "---");
                        totalRegistros++;
                    }
                }
            }
            
            System.out.println("─".repeat(100));
            System.out.println("Total de registros: " + totalRegistros);
            System.out.println("Total de projetos: " + projetos.size() + "\n");
            
        } catch (Exception e) {
            System.err.println("Erro ao listar projetos: " + e.getMessage());
            e.getStackTrace();
        }
    }
}
