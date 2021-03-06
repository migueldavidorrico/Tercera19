import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ExpresionParentesis {
    public static void main(String[] args) {
        JFrame ventana=new JFrame("Expresiones");

        PanelPropio miPanel=new PanelPropio();

        //Comprobar lo del tamaño

        ventana.add(miPanel.getPanel());
        ventana.setResizable(true);
        ventana.setBounds(40,40,1080,170);
        ventana.setLocationRelativeTo(null);
        
        ventana.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                System.out.println(ventana.getWidth());
                System.out.println(ventana.getHeight());
            }
        });

        
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
}

class PanelPropio{



    JLabel etiqueta=new JLabel("Expresión:");
    JTextField texto=new JTextField(20);
    JButton comprobar=new JButton("comprobar");
    JLabel salida=new JLabel("Cadena vacía");

    JPanel panel=new JPanel();

    public PanelPropio() {
        this.panel.add(etiqueta);
        this.panel.add(texto);
//        this.panel.add(comprobar);
        this.panel.add(salida);

        Font fuente=new Font("Arial",Font.ITALIC,42);
        etiqueta.setFont(fuente);
        texto.setFont(fuente);
        salida.setFont(fuente);

        texto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                pulsadoBoton();
            }
        });
        texto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pulsadoBoton();
            }
        });

        comprobar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pulsadoBoton();
            }
        });


    }

    private void pulsadoBoton() {
        String salida="";
        if(texto.getText().equals("")){
            salida+="Cadena vacía";
            this.salida.setForeground(Color.BLACK);
            this.salida.setText(salida);
            return;
        }
        if(valida(texto.getText())){
            salida+="Es válida";
            this.salida.setForeground(Color.GREEN);
        } else {
            this.salida.setForeground(Color.RED);
            salida+="No es válida";
        }
//        JOptionPane.showMessageDialog(null,salida);

        this.salida.setText(salida);
    }

    private static boolean valida(String expresion) {
        Map<Character,Character> validaciones=new HashMap<>();
        validaciones.put('(',')');
        validaciones.put('[',']');
        validaciones.put('{','}');
        Deque<Character> pila = new LinkedList<>();
        char[] caracteres = expresion.toCharArray();
        for (char c :
                caracteres) {
            if (validaciones.containsKey(c)) {
                pila.push(validaciones.get(c));
            } else if (validaciones.containsValue(c)) {
                try {
                    if(pila.pop()!=c){
                        return false;
                    }
                } catch (NoSuchElementException nsee){
                    return false;
                }
            }
        }
        return pila.isEmpty();
    }

    public JTextField getTexto() {
        return texto;
    }

    public JPanel getPanel() {
        return panel;
    }
}
