// BEATRIZ ALVAREZ DE ARRIBA y ELENA FERNANDEZ JIMENEZ

package Model;

import java.util.HashMap;
import Jama.Matrix;

public class Bayes {

	private HashMap<String, Matrix> hashMapM;
	private HashMap<String, Matrix> hashMapC;
	
	public Bayes() {
		hashMapM = new HashMap<String, Matrix>();
		hashMapC = new HashMap<String, Matrix>();
	}
	
	public void learnAlgorithm(double[][] x, String c) {
		Matrix matrixExamples = new Matrix(x);
		
		Matrix M = matrixExamples.getMatrix(new int[]{0}, 0, matrixExamples.getColumnDimension()-1);
		for (int i = 1; i < matrixExamples.getRowDimension(); i++) {
			M.plusEquals(matrixExamples.getMatrix(new int[]{i}, 0, matrixExamples.getColumnDimension()-1));
		}
		M.timesEquals(1.0/matrixExamples.getRowDimension());
		
		Matrix C = new Matrix(matrixExamples.getColumnDimension(), matrixExamples.getColumnDimension());
		Matrix dxm;
		for (int i = 0; i < matrixExamples.getRowDimension(); i++) {
			dxm = matrixExamples.getMatrix(new int[]{i}, 0, matrixExamples.getColumnDimension()-1).minus(M);
			C.plusEquals(dxm.transpose().times(dxm));
		}

		hashMapM.put(c, M);
		hashMapC.put(c, C);
	}
	
	public String algorithmResult(double[] example) {
		String[] classes = hashMapC.keySet().toArray(new String[0]);
		String bestClass = null;
		double bestFunction = Double.NEGATIVE_INFINITY;
		double auxFunction;
		for (String auxClass : classes) {
			auxFunction = auxFunction(example, auxClass);
			if (auxFunction > bestFunction) {
				bestFunction = auxFunction;
				bestClass = auxClass;
			}
		}
		return bestClass;
	}
	
	private double auxFunction(double[] example, String auxClass) {
		Matrix matrix = new Matrix(example, 1);
		if (!hashMapC.containsKey(auxClass))
			return Double.NaN;
		return calculateF(matrix, hashMapM.get(auxClass), hashMapC.get(auxClass));
	}
	
	private static double calculateF(Matrix matrix, Matrix m, Matrix c) {
		int d = matrix.getColumnDimension();
		Matrix aux = matrix.minus(m).times(c.inverse()).times(matrix.minus(m).transpose());
		double exp = -0.5*aux.get(0, 0);
		double base = 1.0/(Math.pow(2*Math.PI,d/2)*Math.pow(c.det(), 0.5));
		return base*Math.exp(exp);
	}
	
}
