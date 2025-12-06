package com.exemplo.orm.entidades;

import jakarta.persistence.*;

import java.util.Date;

// entidade JPA que mapea a tabela 'atividade'

@Entity
@Table(name = "atividade")
public class Atividade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Integer codigo;
    
    @Column(name = "descricao", length = 250)
    private String descricao;
    
    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    
    // relacionamento com Projeto (muitas atividades pertencem a um projeto)
    @ManyToOne
    @JoinColumn(name = "projeto", referencedColumnName = "codigo")
    private Projeto projeto;
    
    // construtores
    public Atividade() {
    }
    
    public Atividade(String descricao, Projeto projeto, Date dataInicio, Date dataFim) {
        this.descricao = descricao;
        this.projeto = projeto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    
    // getters e setters
    public Integer getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Date getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public Date getDataFim() {
        return dataFim;
    }
    
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
    public Projeto getProjeto() {
        return projeto;
    }
    
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    @Override
    public String toString() {
        return "Atividade{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                '}';
    }
}