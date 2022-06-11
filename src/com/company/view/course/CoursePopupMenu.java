package com.company.view.course;

import com.company.model.Course;
import com.company.model.Student;
import com.company.view.student.StudentListPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoursePopupMenu extends JPopupMenu {
    public static MenuActionListener menuActionListener = new MenuActionListener();

    public CoursePopupMenu() {
        add(item("Сохранить", "save"));
        add(item("Удалить", "delete"));
        add(item("Подробнее", "about"));

    }

    private static JMenuItem item(String text, String command) {
        JMenuItem button = new JMenuItem(text);
        button.setActionCommand(command);
        button.addActionListener(menuActionListener);

        return button;
    }

    static class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int rowIndex = CourseListPanel.table.getSelectedRow();
            int id = Integer.parseInt(CourseListPanel.table.getValueAt(rowIndex, 0).toString());
            String title = CourseListPanel.table.getValueAt(rowIndex, 1).toString();
            String description = CourseListPanel.table.getValueAt(rowIndex, 2).toString();

            switch (e.getActionCommand()) {
                case "save" -> Course.update(id, title, description);
                case "delete" -> Course.delete(id, rowIndex);
                case "about" -> System.out.println("Тыкнули Подробнее");
                default -> System.out.println("Неизвестная команда");
            }
        }
    }


}
