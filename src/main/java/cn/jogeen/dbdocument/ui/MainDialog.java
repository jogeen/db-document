package cn.jogeen.dbdocument.ui;

import cn.jogeen.dbdocument.excel.ExcelService;
import cn.jogeen.dbdocument.jdbc.connection.ConnectionUtils;
import cn.jogeen.dbdocument.jdbc.dao.DataBaseDao;
import cn.jogeen.dbdocument.jdbc.dao.DataBaseDaoImpl;
import cn.jogeen.dbdocument.jdbc.model.Column;
import cn.jogeen.dbdocument.jdbc.model.Table;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

/**
 * @Autor  jogeen
 */
public class MainDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField t_address;
    private JTextField t_port;
    private JTextField t_username;
    private JTextField t_password;
    private JButton connect_btn;
    private JList database_list;
    private JButton choose_btn;
    private JLabel path_label;

    private JFileChooser jfc = new JFileChooser(new File("C:\\"));

    MainDialog mainDialog;


    String address;
    Integer port;
    String username;
    String password;
    String database;

    public MainDialog() {
        setTitle("数据库文档生成工具-JoGeen");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        mainDialog=this;
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database = (String) database_list.getSelectedValue();
                DataBaseDao dataBaseDao = new DataBaseDaoImpl(address, port, database, username, password);
                List<Table> tablse = dataBaseDao.showTables(database);
                for (Table table : tablse) {
                    List<Column> columns = dataBaseDao.showColumns(database, table.getTableName());
                    table.setColumnList(columns);
                }
                dataBaseDao.closeConnection();
                ExcelService excelService = new ExcelService();
                try{
                    excelService.buildExcel(path_label.getText(), database, tablse);
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,"生成失败","生成失败",JOptionPane.ERROR_MESSAGE);
                }

                JOptionPane.showMessageDialog(null,"生成成功","操作成功",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        connect_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                address = t_address.getText();
                port = Integer.parseInt(t_port.getText());
                username = t_username.getText();
                password = t_password.getText();
                try {
                    List<String> databaseNames = ConnectionUtils.testConnection(address, port, username, password);
                    if (databaseNames.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"链接失败","链接失败",JOptionPane.WARNING_MESSAGE);
                    }

                    DefaultListModel<String> listModel = new DefaultListModel<String>();
                    for (String databaseName : databaseNames) {
                        listModel.addElement(databaseName);
                    }

                    database_list.setModel(listModel);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null,"链接失败","链接失败",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        choose_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfc.setFileSelectionMode(1);
                int i = jfc.showOpenDialog(null);
                if(i==1){
                    return;
                }else{
                    File selectedFile = jfc.getSelectedFile();
                    path_label.setText(selectedFile.getAbsolutePath());
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {

    }
}
