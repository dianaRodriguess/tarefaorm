package com.exemplo.orm;

import com.exemplo.orm.entidades.Projeto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class Atualizar {
    
    static void atualizarLiderProjeto(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            
            // acha projeto
            Projeto projeto = em.find(Projeto.class, 3);
            
            if (projeto != null) {
                Integer antigoResponsavel = projeto.getResponsavel();
                
                // atualiza o responsável. mudança já é salva automaticamente
                projeto.setResponsavel(8);
                
                tx.commit();
                
                System.out.println("Responsável pelo projeto atualizado!");
                System.out.println("Projeto: " + projeto.getNome());
                System.out.println("Responsável anterior: " + antigoResponsavel);
                System.out.println("Novo responsável: " + projeto.getResponsavel() + "\n");
            } else {
                System.err.println("Projeto não encontrado!");
                tx.rollback();
            }
            
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Erro ao atualizar responsável: " + e.getMessage());
            e.getStackTrace();
        } finally {
            em.close();
        }
    }
}
