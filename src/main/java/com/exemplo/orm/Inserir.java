package com.exemplo.orm;

import com.exemplo.orm.entidades.Atividade;
import com.exemplo.orm.entidades.Projeto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class Inserir {
    
    static void inserirAtividade(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            
            // busca o projeto onde vai ser inserido
            Projeto projeto = em.find(Projeto.class, 4);
            
            if (projeto != null) {
                // cria att nova
                Atividade novaAtividade = new Atividade();
                novaAtividade.setDescricao("AUG - Atividade 8");
                novaAtividade.setProjeto(projeto);
                novaAtividade.setDataInicio(java.sql.Date.valueOf("2018-03-15"));
                novaAtividade.setDataFim(java.sql.Date.valueOf("2018-06-20"));
                
                // salva a atividade no banco
                em.persist(novaAtividade);
                
                tx.commit();
                
                System.out.println("Atividade inserida com sucesso!");
                System.out.println("ID gerado: " + novaAtividade.getCodigo());
                System.out.println("Descrição: " + novaAtividade.getDescricao());
                System.out.println("Projeto: " + projeto.getNome() + "\n");
            } else {
                System.err.println("Projeto não encontrado!");
                tx.rollback();
            }
            
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Erro ao inserir atividade: " + e.getMessage());
            e.getStackTrace();
        } finally {
            em.close();
        }
    }
}
