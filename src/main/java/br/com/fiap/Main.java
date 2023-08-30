package br.com.fiap;

import br.com.fiap.domain.entity.Bem;
import br.com.fiap.domain.entity.Departamento;
import br.com.fiap.domain.entity.Inventario;
import br.com.fiap.domain.entity.TipoDeBem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");

        EntityManager manager = factory.createEntityManager();

        //cadastroDados(manager);

        //buscarInventarioPorId(manager);


        //buscarTodosBens(manager);

        manager.close();
        factory.close();


    }

    private static void buscarTodosBens(EntityManager manager) {
        List<Bem> bens = manager.createQuery("FROM Bem").getResultList();

        bens.forEach(System.out::println);
    }

    private static void buscarInventarioPorId(EntityManager manager) {
        Long id = Long.valueOf(JOptionPane.showInputDialog("ID do inventário: "));
        Inventario inventario = manager.find(Inventario.class, id);
        System.out.println(inventario);
    }

    private static void cadastroDados(EntityManager manager) {
        TipoDeBem tipo = new TipoDeBem(null, "automóvel");
        Departamento departamento = new Departamento(null, "Jurídico");
        Inventario inventario = new Inventario(null, departamento, LocalDate.now(), null, "relatório de processos");
        Bem bem = new Bem(null, "Moto", tipo, "RG6JS7", departamento, LocalDate.of(2019, 7,5));

        manager.getTransaction().begin();
        manager.persist(inventario);
        manager.persist(bem);
        manager.getTransaction().commit();
    }
}