/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reconocimientofacial;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Rubén González V
 */
public class View extends javax.swing.JFrame {

    XStream xstream = new XStream(new DomDriver());
    public int[][][] imagenes;
    public static double[][] distancias;
    int actualImage;
    int actualClick;
    public static double[][] rectas;

    /**
     * Creates new form View
     */
    public View() {
        initComponents();
        imagenes = new int[6][11][2];
        distancias = new double[6][55];
        rectas = new double[6][2];
        actualImage = 0;
        actualClick = 0;
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEscogerImagen = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        labelImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEscogerImagen.setBackground(java.awt.SystemColor.textHighlight);
        btnEscogerImagen.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnEscogerImagen.setForeground(javax.swing.UIManager.getDefaults().getColor("CheckBoxMenuItem.selectionBackground"));
        btnEscogerImagen.setText("Comenzar");
        btnEscogerImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscogerImagenActionPerformed(evt);
            }
        });

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelImagenMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(btnEscogerImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(labelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEscogerImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEscogerImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscogerImagenActionPerformed
        // TODO add your handling code here:
        abrirImagen();
    }//GEN-LAST:event_btnEscogerImagenActionPerformed

    private void labelImagenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImagenMousePressed
        // TODO add your handling code here:
        Graphics g = labelImagen.getGraphics();
        g.setColor(Color.red);
        g.fillOval(evt.getX(), evt.getY(), 5, 5);
        if (actualClick == 10) {
            if (actualImage == 5) {
                calcularDistanciasBase();
                normalizar();
                calcularRecta();
                try {
                    Save();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.setVisible(false);
                Inicio view = new Inicio();
                view.setVisible(true);
            } else {
                label.setText("Siga Con la Siguiete Imagen");
                actualImage++;
                actualClick = 0;
                abrirImagen();
            }
        } else {
            int x = evt.getX();
            int y = evt.getY();
            imagenes[actualImage][actualClick][0] = x;
            imagenes[actualImage][actualClick][1] = y;
            switch (++actualClick) {
                case 1:
                    label.setText("Haga Click en el extremo externo del ojo ubicado en la parte izquierda de la imagen");
                    break;
                case 2:
                    label.setText("Haga Click en el extremo interno del ojo ubicado en la parte izquierda de la imagen");
                    break;
                case 3:
                    label.setText("Haga Click en el extremo interno del ojo ubicado en la parte derecha de la imagen");
                    break;
                case 4:
                    label.setText("Haga Click en el extremo externo del ojo ubicado en la parte derecha de la imagen");
                    break;
                case 5:
                    label.setText("Haga Click en el punto central de la base de la nariz");
                    break;
                case 6:
                    label.setText("Haga Click en el extremo de la boca ubicado en la parte izquierda de la imagen");
                    break;
                case 7:
                    label.setText("Haga Click en el extremo de la boca ubicado en la parte derecha de la imagen");
                    break;
                case 8:
                    label.setText("Haga Click en el punto medio de la parte alta del labio superior");
                    break;
                case 9:
                    label.setText("Haga Click en el punto medio de la parte baja del labio inferior");
                    break;
                case 10:
                    label.setText("Haga Click en el punto medio de la base de la barbilla");
                    break;
            }
        }
    }//GEN-LAST:event_labelImagenMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEscogerImagen;
    private javax.swing.JLabel label;
    private javax.swing.JLabel labelImagen;
    // End of variables declaration//GEN-END:variables

    public void abrirImagen() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.JPG", "jpg");
        fc.setFileFilter(filtro);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            ImageIcon image = new ImageIcon(fc.getSelectedFile().getAbsolutePath());
            Icon i = new ImageIcon(image.getImage());
            Icon icon = new ImageIcon(image.getImage().getScaledInstance((i.getIconWidth() * labelImagen.getHeight()) / i.getIconHeight(), labelImagen.getHeight(), Image.SCALE_DEFAULT));
            labelImagen.setIcon(icon);
        }
        label.setText("Haga Click en el punto central de la línea de cabello");
    }

    private void calcularDistanciasBase() {
        
        for (int i = 0; i < 6; i++) {
            int cont = 0;
            for (int x = 0; x < 11; x++) {
                for (int y = 0; y < 11; y++) {
                    if (x < y) {
                        distancias[i][cont] = Math.hypot((imagenes[i][x][0] - imagenes[i][y][0]), (imagenes[i][x][1] - imagenes[i][y][1]));
                        cont++;
                    }
                }
            }
        }
    }

    private void calcularRecta() {
        for (int i = 0; i < 6; i++) {
            double sumatoriaxy = 0;
            double sumatoriax2 = 0;
            double sumatoriax = 0;
            double sumatoriay = 0;
            for (int x = 0; x < 11; x++) {
                sumatoriax += imagenes[i][x][0];
                sumatoriay += imagenes[i][x][1];
                sumatoriaxy += imagenes[i][x][0] * imagenes[i][x][1];
                sumatoriax2 += imagenes[i][x][0] * imagenes[i][x][0];
            }
            double m = (sumatoriaxy - (sumatoriax * sumatoriay / 11)) / (sumatoriax2 - ((sumatoriax * sumatoriax) / 11));
            double b = (sumatoriay / 11) - (m * (sumatoriax / 11));
            rectas[i][0] = m;
            rectas[i][1] = b;
        }
    }

    private void Save() throws FileNotFoundException {
        try (PrintWriter outA = new PrintWriter("distancias.xml")) {
            String archivo = xstream.toXML(distancias);
            outA.println(archivo);
        }
        try (PrintWriter outA = new PrintWriter("rectas.xml")) {
            String archivo = xstream.toXML(rectas);
            outA.println(archivo);
        }
    }

    private void normalizar() {
        for (int i = 0; i < 6; i++) {
//            double maximo = 0;
//            for (int x = 0; x < 55; x++) {
//                if (distancias[i][x] > maximo) {
//                    maximo = distancias[i][x];
//                }
//            }
            for (int x = 0; x < 55; x++) {
                distancias[i][x] = distancias[i][x] / distancias[i][45];
            }
        }
    }
}
