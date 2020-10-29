package controllers
import akka.util.ByteString
import aview.TUI.TUI
import com.google.inject.Guice
import gamecontrol.controller.ControllerInterface
import play.api.http.HttpEntity
import play.api.mvc.{ResponseHeader, Result}

class GameController {
  val injector = Guice.createInjector(new GameModule)
  val Controller:ControllerInterface = scala.main.Controller;
  //////////////////////////////////////////////////INITIALISIERUNG\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

  var mode = false
  var s = ""


  def run(): Unit = {
    tui.update(Console.WHITE + "Spiel wird gestartet", 0)
    supervisor.state = true

    //state sagt ob spieler1 oder 2 dran ist bzw spieler1 oder bot
    while(true){
      s = scala.io.StdIn.readLine().toString
      supervisor.newRound()
      tui.input(s)
      while(supervisor.newRoundactive()==2){
        s = scala.io.StdIn.readLine().toString
        tui.input(s)
      }
      supervisor.state = !supervisor.state
      if(s == "exit")
        return
    }


  }
  def send(s : String) {
    Result(
      header = ResponseHeader(200, Map.empty),
      body = HttpEntity.Strict(ByteString(s), Some("text/plain"))
    )
  }
}
