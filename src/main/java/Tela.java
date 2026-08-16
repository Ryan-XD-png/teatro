import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela extends JFrame implements ActionListener {
    JPanel header, body, sideBar, valores, radios;
    JLabel quant,laValor;
    JButton finalizar;
    JRadioButton re,can,ocup,reset;
    ButtonGroup opcao;
    JLabel [] legenda;
    JPanel [] legendas;
    Cadeira [][] cadeiras;
    String [] palavras = {"Ocupadas","Reservadas","Livres","","",""};
    char [] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    int [][] cores = {{244, 29, 21},{252, 243, 0},{0, 175, 84},{63, 142, 252}};
    int[] quantidade = {0,0,0,0,0,200};
    int escolha=0,operacao=0,descontos=0;
    double preco=0.00, valorCadeira=50.00;
    Tela(){


        cadeiras =new Cadeira[22][22];
        legendas = new JPanel[3];
        legenda = new JLabel[6] ;


        for(int i =0 ; i<3;i++){
            legendas[i] = new JPanel();
            legendas[i].setBackground(new Color(cores[i%3][0],cores[i%3][1],cores[i%3][2]));
        }
        for(int i = 0; i<6;i++){
            legenda[i]=new JLabel();

            if (i<3){
                legenda[i].setText(palavras[i]);
            }else{
                legenda[i].setText(String.valueOf(quantidade[i]));
            }
            legendas[i%3].add(legenda[i]);
        }
        quant = new JLabel(String.valueOf(escolha));
        quant.setHorizontalAlignment(SwingConstants.CENTER);
        quant.setVerticalAlignment(SwingConstants.CENTER);
        quant.setFont(new Font("Arial",Font.BOLD,20));

        laValor = new JLabel("R$ "+String.valueOf( preco));
        laValor.setHorizontalAlignment(SwingConstants.CENTER);
        laValor.setVerticalAlignment(SwingConstants.CENTER);
        laValor.setFont(new Font("Arial",Font.BOLD,20));

        re =new JRadioButton("Reservar");
        re.setBackground(new Color(255, 255, 255));
        re.setFocusable(false);
        re.addActionListener(this);
        ocup =new JRadioButton("Comprar");
        ocup.setBackground(new Color(255, 255, 255));
        ocup.setFocusable(false);
        ocup.addActionListener(this);
        can =new JRadioButton("Cancelar");
        can.setBackground(new Color(255, 255, 255));
        can.setFocusable(false);
        can.addActionListener(this);
        reset=new JRadioButton("Reset");
        reset.setBackground(new Color(255, 255, 255));
        reset.setFocusable(false);
        reset.addActionListener(this);
        opcao= new ButtonGroup();
        opcao.add(re);
        opcao.add(ocup);
        opcao.add(can);
        opcao.add(reset);

        radios = new JPanel();
        radios.setLayout(new GridLayout(3,1));
        radios.setBackground(new Color(255, 255, 255));
        radios.add(re);
        radios.add(ocup);
        radios.add(can);
        radios.add(reset);

        finalizar = new JButton("Finalizar");
        finalizar.setFocusable(false);
        finalizar.setBackground(new Color(147, 253, 148));
        finalizar.addActionListener(this);

        sideBar = new JPanel();
        sideBar.setLayout(new GridLayout(4,1));
        sideBar.setBackground(new Color(255, 255, 255));
        sideBar.setPreferredSize(new Dimension(200,0));
        sideBar.add(quant);
        sideBar.add(laValor);
        sideBar.add(radios);
        sideBar.add(finalizar);

        body = new JPanel();
        body.setLayout(new GridLayout(22,22,2,2));
        body.setBackground(new Color(255, 255, 255));
        for (int i = 0; i < cadeiras.length; i++) {
            for (int j = 0; j < cadeiras[i].length; j++) {
                cadeiras[i][j]= new Cadeira();

                if((i==0 & j==0) || (i==21& j==0) || (j==21 & i==0) || (j==21 & i ==21)) {
                    cadeiras[i][j].setEnabled(false);
                    cadeiras[i][j].setFocusPainted(false);
                    cadeiras[i][j].setContentAreaFilled(false);
                }
                if((i == 0 || i == 21 || j == 0 || j == 21) &&  !((i == 0 || i == 21) && (j == 0 || j == 21))){
                    cadeiras[i][j].setFocusPainted(false);
                    cadeiras[i][j].setContentAreaFilled(false);
                    cadeiras[i][j].setRolloverEnabled(false);
                    cadeiras[i][j].setFocusable(false);
                    if(i==0 || i==21){
                        cadeiras[i][j].setFont(new Font("Arial",Font.BOLD,10));
                        cadeiras[i][j].setForeground(Color.BLACK);
                        cadeiras[i][j].setText(String.valueOf(j));
                    }
                   if (j==0 || j==21){
                       cadeiras[i][j].setFont(new Font("Arial",Font.BOLD,10));
                       cadeiras[i][j].setForeground(Color.BLACK);
                        cadeiras[i][j].setText(String.valueOf(letras[i-1]));
                    }
                }else {
                    cadeiras[i][j].addActionListener(this);
                    cadeiras[i][j].setBackground(new Color(cores[2][0],cores[2][1],cores[2][2]));
                }

                body.add(cadeiras[i][j]);
            }
        }


        header = new JPanel();
        header.setLayout(new GridLayout(1,3));
        header.setBackground(new Color(255, 255, 255));
        for (int i = 0; i < 3; i++) {
            header.add(legendas[i]);
        }

        this.setSize(1250,850);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(body,BorderLayout.CENTER);
        this.add(header,BorderLayout.NORTH);
        this.add(sideBar,BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < cadeiras.length; i++) {
            for (int j = 0; j < cadeiras[i].length; j++) {
                if(e.getSource()== cadeiras[i][j]){

                    switch (cadeiras[i][j].tipo){
                        case (0):
                            escolha+=1;
                            cadeiras[i][j].setBackground(new Color(cores[3][0],cores[3][1],cores[3][2]));
                            cadeiras[i][j].tipo=1;
                            break;
                        case (1):
                            escolha-=1;
                            cadeiras[i][j].setBackground(new Color(cores[2][0],cores[2][1],cores[2][2]));
                            cadeiras[i][j].tipo=0;
                            break;
                        case (2):
                            JOptionPane.showMessageDialog(null,"Lugar Ocupado esta ação não pode ser realizada","Lugar Ocupado",JOptionPane.ERROR_MESSAGE);
                            break;
                        case (3):
                            escolha+=1;
                            cadeiras[i][j].setBackground(new Color(cores[3][0],cores[3][1],cores[3][2]));
                            cadeiras[i][j].tipo=4;
                            break;
                        case (4):
                            escolha-=1;
                            cadeiras[i][j].setBackground(new Color(cores[1][0],cores[1][1],cores[1][2]));
                            cadeiras[i][j].tipo=3;
                            break;
                    }
                    quant.setText(String.valueOf(escolha));
                }
            }}
        if (e.getSource()==re){
            operacao=1;
            for (int l = 0; l < cadeiras.length; l++) {
                for (int k = 0; k < cadeiras[l].length; k++) {
                    if (cadeiras[l][k].tipo==4){
                        escolha-=1;
                        cadeiras[l][k].setBackground(new Color(cores[1][0],cores[1][1],cores[1][2]));
                        cadeiras[l][k].tipo=3;
                        descontos+=1;
                    }
                }
            }
            quant.setText(String.valueOf(escolha));
            laValor.setText("R$ "+String.valueOf(0.4*calc(valorCadeira,escolha,descontos)));
            }
        if (e.getSource()==ocup){
            operacao=2;
            for (int l = 0; l < cadeiras.length; l++) {
                for (int k = 0; k < cadeiras[l].length; k++) {
                    if (cadeiras[l][k].tipo==4){
                        descontos+=1;
                    }
                }
            }
            laValor.setText("R$ "+String.valueOf(calc(valorCadeira,escolha,descontos)));
        }
        if (e.getSource()==can){
            operacao=3;
            laValor.setText("R$ "+0.0);
        }
        if(e.getSource()==reset){

            for (int l = 0; l < cadeiras.length; l++) {
                for (int k = 0; k < cadeiras[l].length; k++) {
                    if (cadeiras[l][k].tipo==4){
                        escolha-=1;
                        cadeiras[l][k].setBackground(new Color(cores[1][0],cores[1][1],cores[1][2]));
                        cadeiras[l][k].tipo=3;
                    }
                    else if(cadeiras[l][k].tipo==1){
                        escolha-=1;
                        cadeiras[l][k].setBackground(new Color(cores[2][0],cores[2][1],cores[2][2]));
                        cadeiras[l][k].tipo=0;
                    }

                }
            }

            quant.setText(String.valueOf(escolha));
            laValor.setText("R$ "+0.0);
        }
        if (e.getSource()==finalizar){
            switch (operacao){
                case 1:
                    for (int l = 0; l < cadeiras.length; l++) {
                        for (int k = 0; k < cadeiras[l].length; k++) {
                            if (cadeiras[l][k].tipo==4){
                                escolha-=1;
                                cadeiras[l][k].setBackground(new Color(cores[1][0],cores[1][1],cores[1][2]));
                                cadeiras[l][k].tipo=0;
                            }
                            else if(cadeiras[l][k].tipo==1){
                                escolha-=1;
                                cadeiras[l][k].setBackground(new Color(cores[1][0],cores[1][1],cores[1][2]));
                                cadeiras[l][k].tipo=0;
                            }

                        }
                    }
                    break;
                case 3:
                    for (int l = 0; l < cadeiras.length; l++) {
                        for (int k = 0; k < cadeiras[l].length; k++) {
                            if (cadeiras[l][k].tipo==4){
                                escolha-=1;
                                cadeiras[l][k].setBackground(new Color(cores[2][0],cores[2][1],cores[2][2]));
                                cadeiras[l][k].tipo=0;
                            }
                            else if(cadeiras[l][k].tipo==1){
                                escolha-=1;
                                cadeiras[l][k].setBackground(new Color(cores[2][0],cores[2][1],cores[2][2]));
                                cadeiras[l][k].tipo=0;
                            }
                        }
                    }
                    break;
                case 2:
                    for (int l = 0; l < cadeiras.length; l++) {
                        for (int k = 0; k < cadeiras[l].length; k++) {
                            if (cadeiras[l][k].tipo==4){
                                escolha-=1;
                                cadeiras[l][k].setBackground(new Color(cores[0][0],cores[0][1],cores[0][2]));
                                cadeiras[l][k].tipo=2;
                            }
                            else if(cadeiras[l][k].tipo==1){
                                escolha-=1;
                                cadeiras[l][k].setBackground(new Color(cores[0][0],cores[0][1],cores[0][2]));
                                cadeiras[l][k].tipo=2;
                            }

                        }
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Nenhuma opção escolhida","Erro ",JOptionPane.ERROR_MESSAGE);
                    break;
            }
            reset.setSelected(true);

            laValor.setText("R$ "+0.0);
            quant.setText(String.valueOf(escolha));
            preco=0;
            operacao=0;

        }
        }
    public double calc (double valorCadeira, int quantidade,int desconto){
        double finalPreco;
        finalPreco= (valorCadeira*quantidade)-((valorCadeira*desconto)*0.4);
        return finalPreco;
    }
}
