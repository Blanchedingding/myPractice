package api;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class CalendarFrame extends JFrame implements ActionListener {

    class CalendarBean {
        String day[];
        int year=2005,month=0;

        public void setYear(int year){ this.year=year; }

        public int getYear(){ return year; }

        public void setMonth(int month){ this.month=month; }

        public int getMonth() { return month; }

        public String[] getCalendar(){
            String a[]=new String[42];
            Calendar date=Calendar.getInstance();
            date.set(year,month-1,1);
            int week=date.get(Calendar.DAY_OF_WEEK)-1;
            int day=0;

            //判断大月份
            if(month==1||month==3||month==5||month==7 ||month==8||month==10||month==12){
                day=31;
            }
            //判断小月
            if(month==4||month==6||month==9||month==11){
                day=30;
            }
            //判断平年与闰年
            if(month==2){
                if(((year%4==0)&&(year%100!=0))||(year%400==0)){
                    day=29;
                } else{
                    day=28;
                }
            }

            for(int i=week,n=1;i<week+day;i++) {
                a[i]=String.valueOf(n) ;
                n++;
            }
            return a;
        }

    }



    JLabel labelDay[]=new JLabel[42];
    JTextField  text=new JTextField(10);
    JButton titleName[]=new JButton[7];
    JButton button = new JButton();
    String name[]={"日","一","二","三", "四","五","六"};
    JButton nextMonth,previousMonth;
    int year=1996,month=1; //启动程序显示的日期信息
    CalendarBean calendar;
    JLabel showMessage=new JLabel("",JLabel.CENTER);
    JLabel lbl1 = new JLabel("请输入年份：");
    JLabel lbl2=new JLabel("      ");

    public CalendarFrame(){
        setBackground(new Color(0, 128, 128));
        JPanel pCenter=new JPanel();
        pCenter.setBackground(new Color(0, 139, 139));
        pCenter.setLayout(new GridLayout(7,7));

        //pCenter添加组件titleName[i]
        for(int i=0;i<7;i++){
            titleName[i]=new JButton(name[i]);
            pCenter.add(titleName[i]);
        }

        for(int i=0;i<42;i++) {
            labelDay[i]=new JLabel("",JLabel.CENTER);
            pCenter.add(labelDay[i]);
        }

        text.addActionListener(this);
        calendar=new CalendarBean();
        calendar.setYear(year);
        calendar.setMonth(month);
        String day[]=calendar.getCalendar();

        for(int i=0;i<42;i++){
            labelDay[i].setText(day[i]);
        }

        nextMonth=new JButton("下月");
        previousMonth=new JButton("上月");
        button=new JButton("确定");

        nextMonth.addActionListener(this);
        previousMonth.addActionListener(this);
        button.addActionListener(this);

        JPanel pNorth=new JPanel(),
                pSouth=new JPanel();
        pNorth.add(showMessage);
        pNorth.add(lbl2);
        pNorth.add(previousMonth);
        pNorth.add(nextMonth);
        pSouth.add(lbl1);
        pSouth.add(text);
        pSouth.add(button);
        showMessage.setText("日历："+calendar.getYear()+"年"+ calendar.getMonth()+"月" );
        ScrollPane scrollPane=new ScrollPane();
        scrollPane.add(pCenter);
        getContentPane().add(scrollPane,BorderLayout.CENTER);// 窗口添加scrollPane在中心区域
        getContentPane().add(pNorth,BorderLayout.NORTH);// 窗口添加pNorth 在北面区域
        getContentPane().add(pSouth,BorderLayout.SOUTH);// 窗口添加pSouth 在南区域。

    }

    public boolean isToday(int year, int month, int day){
        System.out.println(year + " " + month + " " + day);
        Calendar c = Calendar.getInstance();
        System.out.println("====" +c.get(Calendar.YEAR)+" " + (c.get(Calendar.MONTH) + 1) + " " + c.get(Calendar.DATE) );
        if((year == c.get(Calendar.YEAR)) && (month == c.get(Calendar.MONTH) + 1) && (day == c.get(Calendar.DATE))){
            return true;
        } else {
            return false;
        }
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource()==nextMonth) {
            month=month+1;
            if(month>12)
                month=1;
            calendar.setMonth(month);
            String day[]=calendar.getCalendar();

            for(int i=0;i<42;i++) {
                labelDay[i].setText(day[i]);
                if(day[i] != null && isToday(calendar.getYear(), month, Integer.valueOf(day[i]))){
                    labelDay[i].setForeground(Color.red);
                } else {
                    labelDay[i].setForeground(Color.black);
                }
            }
        }
        else if(e.getSource()==previousMonth) {
            month=month-1;
            if(month<1)
                month=12;
            calendar.setMonth(month);
            String day[]=calendar.getCalendar();

            for(int i=0;i<42;i++) {
                labelDay[i].setText(day[i]);
                if(day[i] != null && isToday(calendar.getYear(), month, Integer.valueOf(day[i]))){
                    labelDay[i].setForeground(Color.red);
                } else {
                    labelDay[i].setForeground(Color.black);
                }
            }
        }
        else if(e.getSource()==button) {
            if(month>12)
                month=1;
            calendar.setYear(Integer.parseInt(text.getText()));
            String day[]=calendar.getCalendar();
            for(int i=0;i<42;i++) {
                labelDay[i].setText(day[i]);
                if(day[i] != null && isToday(calendar.getYear(), month, Integer.valueOf(day[i]))){
                    labelDay[i].setForeground(Color.red);
                } else {
                    labelDay[i].setForeground(Color.black);
                }
            }
        }
        showMessage.setText("日历："+calendar.getYear()+"年"+calendar.getMonth()+"月" );
    }

    public static void main(String[] args) {
        CalendarFrame frame = new CalendarFrame();
        frame.setBounds(100,100,360,300);
        frame.setTitle("日历小程序");
        frame.setLocationRelativeTo(null);//窗体居中显示
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
