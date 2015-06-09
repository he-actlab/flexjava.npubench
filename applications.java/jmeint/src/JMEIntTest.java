
/**
	Evaluation for FlexJava framework
*/

import java.util.Random;
import Parrot.ParrotObserver;
import com.googlecode.fannj.Fann;
import java.io.IOException;

public class JMEIntTest {
	public static Random rand;
	public static Vector3f randvec() {
		Vector3f vec = new Vector3f(
				rand.nextFloat(), rand.nextFloat(), rand.nextFloat()
				);
		return vec;
	}
	public static void main(String[] argv) throws IOException {

		rand = new Random(Integer.parseInt(argv[0])); 

		for (int i = 0; i <= 100; ++i) {


			Vector3f vec1 = randvec();
			Vector3f vec2 = randvec();
			Vector3f vec3 = randvec();
			Vector3f vec4 = randvec();
			Vector3f vec5 = randvec();
			Vector3f vec6 = randvec();

			Double pIn[] = new Double[18];

			pIn[0] = new Double ((double) vec1.x);
			pIn[1] = new Double ((double) vec1.y);
			pIn[2] = new Double ((double) vec1.z);

			pIn[3] = new Double ((double) vec2.x);
			pIn[4] = new Double ((double) vec2.y);
			pIn[5] = new Double ((double) vec2.z);

			pIn[6] = new Double ((double) vec3.x);
			pIn[7] = new Double ((double) vec3.y);
			pIn[8] = new Double ((double) vec3.z);

			pIn[9]  = new Double ((double) vec4.x);
			pIn[10] = new Double ((double) vec4.y);
			pIn[11] = new Double ((double) vec4.z);

			pIn[12] = new Double ((double) vec5.x);
			pIn[13] = new Double ((double) vec5.y);
			pIn[14] = new Double ((double) vec5.z);

			pIn[15] = new Double ((double) vec6.x);
			pIn[16] = new Double ((double) vec6.y);
			pIn[17] = new Double ((double) vec6.z);

			boolean isec;
			double result [] = new double[2];

#begin_loosen parrot(input, "jmeint", [18]<0.0; 1.0>pIn)

			isec = Intersection.intersection(
					randvec(), randvec(), randvec(),
					randvec(), randvec(), randvec()
					);

			if(isec)
			{
				result[0] = 0.2;
				result[1] = 0.8;
			}
			else
			{
				result[0] = 0.8;
				result[1] = 0.2;
			}

#end_loosen parrot(output, "jmeint", [2]<0.0; 1.0>result)

	
			if(result[0] > result[1])
			{
				isec = false;
			}
			else
			{
				isec = true;
			}

			if (isec) {
				System.out.print("1 ");
			} else {
				System.out.print("0 ");
			}
		}
		System.out.println("");
	}

}
