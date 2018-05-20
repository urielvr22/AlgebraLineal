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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Rubén González V
 */
public class ReconocimientoFacial extends javax.swing.JFrame {

    XStream xstream = new XStream(new DomDriver());
    FileReader reader = null;
    public int[][] imagenMeta;
    public double[][] distanciaMeta;
    public double[] distancia;
    public double m;
    public double b;
    public int actualClick;
    ArrayList<String> nombres = new ArrayList<>();

    /**
     * Creates new form ReconocimientoFacial
     *
     * @throws java.io.FileNotFoundException
     */
    public ReconocimientoFacial() throws FileNotFoundException {
        Open();
        initComponents();
        imagenMeta = new int[11][2];
        distanciaMeta = new double[11][11];
        distancia = new double[6];
        nombres.add("Tom Cruise");
        nombres.add("Brad Pitt");
        nombres.add("Keanu Reeves");
        nombres.add("Keira Knightley");
        nombres.add("Felicity Jones");
        nombres.add("Adele");
        
        
        
        
        
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

        btnEscogerImagen1 = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        labelImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEscogerImagen1.setBackground(java.awt.SystemColor.textHighlight);
        btnEscogerImagen1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btnEscogerImagen1.setForeground(javax.swing.UIManager.getDefaults().getColor("CheckBoxMenuItem.selectionBackground"));
        btnEscogerImagen1.setText("Comenzar");
        btnEscogerImagen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscogerImagen1ActionPerformed(evt);
            }
        });

        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelImagenMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(btnEscogerImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(labelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEscogerImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEscogerImagen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscogerImagen1ActionPerformed
        // TODO add your handling code here:
        imagenMeta = new int[11][2];
        distanciaMeta = new double[11][11];
        distancia = new double[6];
        actualClick = 0;
        abrirImagen();
    }//GEN-LAST:event_btnEscogerImagen1ActionPerformed

    private void labelImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImagenMouseClicked
        // TODO add your handling code here:
        Graphics g = labelImagen.getGraphics();
        g.setColor(Color.red);
        g.fillOval(evt.getX(), evt.getY(), 5, 5);
        if (actualClick == 10) {
            calcularDistanciasMeta();
            normalizar();
            calcularRecta();
            System.out.println("m"+m+"b"+b);
            for (int i = 0; i<6 ; i++)
            {
                System.out.println("m: "+View.rectas[i][0]+", b: "+View.rectas[i][1]);
            }
            calcularDistancias();
            resultado();
        } else {
            int x = evt.getX();
            int y = evt.getY();
            imagenMeta[actualClick][0] = x;
            imagenMeta[actualClick][1] = y;
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
    }//GEN-LAST:event_labelImagenMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEscogerImagen1;
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

    private void calcularDistanciasMeta() {
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
                distanciaMeta[x][y] = Math.hypot((imagenMeta[x][0] - imagenMeta[y][0]), (imagenMeta[x][1] - imagenMeta[y][1]));
            }
        }
    }

    private void calcularDistancias() {
        for (int i = 0; i < 6; i++) {
            double distance = 0;
            for (int x = 0; x < 11; x++) {
                for (int y = 0; y < 11; y++) {
                    distance += Math.abs(View.distancias[i][x][y] - distanciaMeta[x][y]);
                }
            }
            distancia[i] = distance;
        }
    }

    private void resultado() {
        double result = distancia[0];
        int person = 0;
        String s = "";
        for (int x = 0; x < distancia.length; x++) {
            s += nombres.get(x) + ": " + distancia[x] + "\n";
            if (result > distancia[x]) {
                result = distancia[x];
                person = x;
            }
        }
        JOptionPane.showMessageDialog(this, "La imagen pertenece a la cara de: " + nombres.get(person) + "\n\n" + s);
    }

    private void Open() throws FileNotFoundException {
        reader = new FileReader("distancias.xml");
        View.distancias = (double[][][]) (xstream.fromXML(reader));
        reader = new FileReader("rectas.xml");
        View.rectas = (double[][]) (xstream.fromXML(reader));
    }

    private void normalizar() {
        double maximo = 0;
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
                if (distanciaMeta[x][y] > maximo) {
                    maximo = distanciaMeta[x][y];
                }
            }
        }
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
                distanciaMeta[x][y] = distanciaMeta[x][y] / maximo;
            }
        }
    }

    private void calcularRecta() {
        double sumatoriaxy = 0;
        double sumatoriax2 = 0;
        double sumatoriax = 0;
        double sumatoriay = 0;
        for (int x = 0; x < 11; x++) {
            sumatoriax += imagenMeta[x][0];
            sumatoriay += imagenMeta[x][1];
            sumatoriaxy += imagenMeta[x][0] * imagenMeta[x][1];
            sumatoriax2 += imagenMeta[x][0] * imagenMeta[x][0];
        }
        m = (sumatoriaxy - (sumatoriax * sumatoriay / 11)) / (sumatoriax2 - ((sumatoriax * sumatoriax) / 11));
        b = (sumatoriay / 11) - (m * (sumatoriax / 11));

    }
}
