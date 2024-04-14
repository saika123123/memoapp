import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import bean.StudentDTO;

@WebServlet("/editmemo")
public class EditStudentServlet extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    String msg = "学習の情報を表示します";
    //入力値（btn）を取得
    req.setCharacterEncoding("utf-8");
    String btn = req.getParameter("btn");
    //StudentDAOオブジェクトを生成
    StudentDAO3 sdao = new StudentDAO3();
    //押下ボタンによる分岐処理
    if(btn.equals("追加")){
      String date = req.getParameter("date");
      String name = req.getParameter("name");
      String content = req.getParameter("content");
      sdao.insert(date, name, content);
      msg = "日付" + date + "の内容を追加しました";
    } else if(btn.equals("修正")) {
      String date = req.getParameter("date");
      String name = req.getParameter("name");
      String content = req.getParameter("content");
      sdao.update(date, name, content);
      msg = "日付" + date + "の内容を修正しました";
    } else if(btn.equals("削除")) {
      String date = req.getParameter("date");
      sdao.delete(date);
      msg = "日付" + date + "の内容を削除しました";
    }
    //全件検索
    StudentDTO sdto = sdao.select();
    //リクエストスコープにDTOとmsgを格納
    req.setAttribute("sdto", sdto);
    req.setAttribute("msg", msg);
    //JSPにフォワード
    RequestDispatcher rd = req.getRequestDispatcher("/editstudent.jsp");
    rd.forward(req, res);
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    doPost(req, res);
  }
}

