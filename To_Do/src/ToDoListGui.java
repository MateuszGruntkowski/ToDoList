import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGui extends JFrame implements ActionListener {

    private JPanel taskPanel, taskComponentPanel;
    public ToDoListGui(){
        super("To Do List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstans.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground((new Color(29, 53, 87)));
        setLayout(null);

        addGuiComponents();
    }

    private void addGuiComponents(){

        //baner text
        JLabel bannerLabel = new JLabel("TO DO LIST");
        bannerLabel.setFont(new Font("Dialog", Font.PLAIN, 36));
        bannerLabel.setForeground(new Color(241, 250, 238));
        bannerLabel.setBounds((CommonConstans.GUI_SIZE.width - bannerLabel.getPreferredSize().width)/2,
                15,
                CommonConstans.Banner_Size.width,
                CommonConstans.Banner_Size.height);
        this.getContentPane().add(bannerLabel);

        //taskpanel
        taskPanel = new JPanel();
        taskPanel.setBackground(new Color(69, 123, 157));

        //taskcomponentPanel
        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        //add scrolling to the task panel
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(8, 70, CommonConstans.TASKPANEL_SIZE.width, CommonConstans.TASKPANEL_SIZE.height);
        scrollPane.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        scrollPane.setMaximumSize(CommonConstans.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(new Color(168, 218, 220));


        //add task button
        JButton addTaskButton = new JButton("ADD TASK");
        addTaskButton.setBounds(-5, CommonConstans.GUI_SIZE.height-88,
                CommonConstans.ADDTASK_BUTTON_SIZE.width, CommonConstans.ADDTASK_BUTTON_SIZE.height);
        addTaskButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        addTaskButton.setBackground(new Color(230, 57, 70));
        addTaskButton.setForeground(new Color(241, 250, 238));
        addTaskButton.setFocusable(false);
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.addActionListener(this);

        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("Add Task")){
            // create a task component
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            // make the previous task appear disabled
            if(taskComponentPanel.getComponentCount() > 1){
                TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);
            }

            // make the task field request focus after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }
}
