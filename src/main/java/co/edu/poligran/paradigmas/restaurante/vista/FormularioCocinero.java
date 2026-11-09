/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.poligran.paradigmas.restaurante.vista;

import java.awt.*;
import javax.swing.*;

public class FormularioCocinero extends JFrame {

    public FormularioCocinero(Object datos) {
        setTitle("PoliRestaurante - Vista Cocina");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel lbl = new JLabel("Bienvenido al Panel de Cocina", JLabel.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        add(lbl, BorderLayout.CENTER);
    }
}
