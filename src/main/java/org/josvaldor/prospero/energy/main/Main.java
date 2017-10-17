/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Author(s):
 * Martin Vézina mgvez Github
 * Chen Huang ALEXMORF Github
 * Joaquin Rodriguez
 */
package org.josvaldor.prospero.energy.main;

import org.josvaldor.prospero.energy.system.Solar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

/**
 *
 * @author jorodriguez
 */
public class Main {
	public static void main(String[] args) {
		Solar solar = new Solar(new GregorianCalendar(2016, 0, 1));
		JFrame window = new JFrame("Prospero");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		SolarPanel solarPanel = new SolarPanel();
		solarPanel.setSolar(solar);
		window.add(solarPanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
