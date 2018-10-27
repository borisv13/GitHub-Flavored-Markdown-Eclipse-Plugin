package autoComplete;

import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import append.AddStuffToString;

public class autoComplete {
	public static void main(String[] args) {
		Display display = Display.getDefault();//��ȡDisplay
		Shell shell = new Shell();
		shell.setSize(300, 150); //���ô����С
		shell.setLocation(500, 200);//���ô���λ��
		shell.setText("�Զ���ȫ");//���ñ���
		 
		 
		//Text�ؼ�
		Text text = new Text(shell, SWT.BORDER);//���һ��Text�ؼ�
		text.setBounds(50, 20, 180, 28);//����Shellû�мӲ���,������������λ�úʹ�С
		 
		//Text�ؼ����Զ���ȫ
		MyContentAdapter adapter = new MyContentAdapter();
		
		String [] str = {"heading1","heading2","heading3","heading4","heading5","heading6"};
		SimpleContentProposalProvider proposalProvider = new SimpleContentProposalProvider(str);
		proposalProvider.setFiltering(true);
		AutoCompleteField auto_complete = new AutoCompleteField(text,adapter,str);	 
		 
		shell.open();
		while (!shell.isDisposed()) {//ѭ�����ô���ص�
		    if (!display.readAndDispatch()) {
		        display.sleep();
		    }
		}
		display.dispose();


	}
}
