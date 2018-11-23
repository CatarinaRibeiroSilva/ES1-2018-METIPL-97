
package projecto;

import java.util.ArrayList;
import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;

public class FacebookFeed {
	private ArrayList<Publicacao> publicacoes;
	private GUI window;
	public FacebookFeed(GUI window) {
		/* 
		 * Facebook API Tutorials in Java # 1 | Setup Development Environment 
		 * https://www.youtube.com/watch?v=m14hYs1T3FA&index=1&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb
		 */
		/* 
		 * Facebook API Tutorials in Java # 2 | Get User Access Token
		 * https://www.youtube.com/watch?v=GwbO_PdwK_4&index=2&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb
		 */
		String accessToken2 = "EAABZCxnAyyqwBADSUuMoMAjLjM2eZAiX9rdxLdi2hzEdER9myyYHqbLEVGca5wqlN5OOh8OsFTqMgtkQryXaXciWZBZCJdjTDSNYP17Y0mdA9PZB3QdIDsB4vSorszZBJacoS44L9snyC0rAe9TYvni3F96lFfBOceaqs4YesAjhN0RQRTMR6U1r0ZC0JRBypZBc83pY4aY5dgZDZD";
		FacebookClient fbClient2 = new DefaultFacebookClient(accessToken2);
		User me2 = fbClient2.fetchObject("me", User.class);
		System.out.println("Facebook:");
		System.out.println("Id: " + me2.getId());
		System.out.println("Name: " + me2.getName());

		/* 
		 * Facebook API Tutorials in Java # 4 | Create Your Own Fb APP & Extend User Access Token  
		 * https://www.youtube.com/watch?v=qFZazZ1JXsM&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb&index=5
		 */
		String accessToken4 = "EAABZCxnAyyqwBADSUuMoMAjLjM2eZAiX9rdxLdi2hzEdER9myyYHqbLEVGca5wqlN5OOh8OsFTqMgtkQryXaXciWZBZCJdjTDSNYP17Y0mdA9PZB3QdIDsB4vSorszZBJacoS44L9snyC0rAe9TYvni3F96lFfBOceaqs4YesAjhN0RQRTMR6U1r0ZC0JRBypZBc83pY4aY5dgZDZD";
		FacebookClient fbClient4 = new DefaultFacebookClient(accessToken4);
		AccessToken extendedAccessToken4 = fbClient4.obtainExtendedAccessToken("140490262629036","8bf7ae051a204a555319dfd33c1165d9");
		System.out.println("ExtendedAccessToken: "+extendedAccessToken4.getAccessToken());
		System.out.println("Expires: " + extendedAccessToken4.getExpires());
	}
	
	public void getTimeLinePost() {
		/* 
		 * Facebook API Tutorials in Java # 5 | Get User Timeline Posts
		 * https://www.youtube.com/watch?v=wiFif4gOdFE&index=6&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb
		*/ 
		String accessToken5 ;//= "EAABZCxnAyyqwBADSUuMoMAjLjM2eZAiX9rdxLdi2hzEdER9myyYHqbLEVGca5wqlN5OOh8OsFTqMgtkQryXaXciWZBZCJdjTDSNYP17Y0mdA9PZB3QdIDsB4vSorszZBJacoS44L9snyC0rAe9TYvni3F96lFfBOceaqs4YesAjhN0RQRTMR6U1r0ZC0JRBypZBc83pY4aY5dgZDZD";
		accessToken5 = "";	
		FacebookClient fbClient5 = new DefaultFacebookClient(accessToken5);

		Connection<Post> result = fbClient5.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter5 = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "Inform"
				if (aPost.getMessage() != null && aPost.getMessage().contains("ISCTE")) {
					System.out.println("---- Post "+ counter5 + " ----");
					System.out.println("Id: "+"fb.com/"+aPost.getId());
					System.out.println("Message: "+aPost.getMessage());
					System.out.println("Created: "+aPost.getCreatedTime());
					counter5++;
					//Publicacao p = new Publicacao ("Facebook",aPost.getId(), aPost.getMessage() ,aPost.getCreatedTime());
					//publicacoes.add(p);
				}
				counterTotal++;
			}
		}
		System.out.println("-------------\nN� of Results: " + counter5+"/"+counterTotal);
		window.update(publicacoes);	
	}		

	public ArrayList<Publicacao> getPosts() {
		return publicacoes;
	}
}
