import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import bean.StudentDTO;

@WebServlet("/editmemo")
public class EditStudentServlet extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    String msg = "�w�K�̏���\�����܂�";
    //���͒l�ibtn�j���擾
    req.setCharacterEncoding("utf-8");
    String btn = req.getParameter("btn");
    //StudentDAO�I�u�W�F�N�g�𐶐�
    StudentDAO3 sdao = new StudentDAO3();
    //�����{�^���ɂ�镪�򏈗�
    if(btn.equals("�ǉ�")){
      String date = req.getParameter("date");
      String name = req.getParameter("name");
      String content = req.getParameter("content");
      sdao.insert(date, name, content);
      msg = "���t" + date + "�̓��e��ǉ����܂���";
    } else if(btn.equals("�C��")) {
      String date = req.getParameter("date");
      String name = req.getParameter("name");
      String content = req.getParameter("content");
      sdao.update(date, name, content);
      msg = "���t" + date + "�̓��e���C�����܂���";
    } else if(btn.equals("�폜")) {
      String date = req.getParameter("date");
      sdao.delete(date);
      msg = "���t" + date + "�̓��e���폜���܂���";
    }
    //�S������
    StudentDTO sdto = sdao.select();
    //���N�G�X�g�X�R�[�v��DTO��msg���i�[
    req.setAttribute("sdto", sdto);
    req.setAttribute("msg", msg);
    //JSP�Ƀt�H���[�h
    RequestDispatcher rd = req.getRequestDispatcher("/editstudent.jsp");
    rd.forward(req, res);
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    doPost(req, res);
  }
}

