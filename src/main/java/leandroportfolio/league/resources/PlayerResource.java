package leandroportfolio.league.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Beans.BeanExample;
import leandroportfolio.league.dao.PlayerRepository;
import leandroportfolio.league.model.Player;

@Controller
@RequestMapping("hello")
public class PlayerResource {

	@Autowired
	PlayerRepository playerRepository;
	
	
	
	@ResponseBody
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public BeanExample getMsg() {
			//@PathVariable("param") String msg
        System.out.println( "Hello World!" );
        /*
        for(Player p : playerService.getAllPlayers()) {
        	System.out.println("Player name = "+ p.getName());
        }

		String output = "Jersey sayyy : " + msg;
         */
        System.out.println("Not implemented yet.....");
		BeanExample b = new BeanExample();
		b.setName("name");
		b.setAge("123");
		
		return b;

	}
	

	@ResponseBody
	@RequestMapping(value = "test123/{param}", method = RequestMethod.GET)
	public BeanExample getMsg2(@PathVariable(value="param") String msg) {
        System.out.println( "Hello World 2!" );	
        
		Player player = new Player();
		player.setName(msg);
		player.setEmail(msg+"@mail.com");

		playerRepository.createPlayer(player);
		BeanExample b = new BeanExample();
		b.setName(msg);
		b.setAge("12345");
		
		return b;

	}
	
	
}
