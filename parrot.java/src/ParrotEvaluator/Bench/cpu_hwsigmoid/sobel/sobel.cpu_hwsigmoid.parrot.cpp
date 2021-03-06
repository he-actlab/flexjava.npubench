/*
 * Neural Parrot!
 *
 * Hadi Esmaeilzadeh
 * <hadianeh@cs.washington.edu>
 */

#include "parrot_cpu_hwsigmoid.h"

static float a[8];
static float m[72];
static float w[89];
static float n[8];

/** 9 -> 8 -> 1 */
void parrot(float* x, float* y) {
	m[0] = x[0] * /* w[0] */ -0.868778153393f;
	m[1] = x[0] * /* w[1] */ -2.59073100307f;
	m[2] = x[0] * /* w[2] */ -0.412991545886f;
	m[3] = x[0] * /* w[3] */ 1.06179841974f;
	m[4] = x[0] * /* w[4] */ -0.180986588389f;
	m[5] = x[0] * /* w[5] */ -20.6376913634f;
	m[6] = x[0] * /* w[6] */ -31.3427401458f;
	m[7] = x[0] * /* w[7] */ 4.81852673576f;
	// End of neuron

	m[8] = x[1] * /* w[8] */ -0.538384432068f;
	m[9] = x[1] * /* w[9] */ -6.54342933337f;
	m[10] = x[1] * /* w[10] */ 0.15246409675f;
	m[11] = x[1] * /* w[11] */ 1.3988915229f;
	m[12] = x[1] * /* w[12] */ 0.231780461643f;
	m[13] = x[1] * /* w[13] */ -8.25298663443f;
	m[14] = x[1] * /* w[14] */ -12.375343661f;
	m[15] = x[1] * /* w[15] */ -17.3711276575f;
	// End of neuron

	m[16] = x[2] * /* w[16] */ 1.07849917023f;
	m[17] = x[2] * /* w[17] */ -2.57690368677f;
	m[18] = x[2] * /* w[18] */ 0.750384476703f;
	m[19] = x[2] * /* w[19] */ 14.060847441f;
	m[20] = x[2] * /* w[20] */ 0.670704656862f;
	m[21] = x[2] * /* w[21] */ 2.83779604079f;
	m[22] = x[2] * /* w[22] */ 0.52832478129f;
	m[23] = x[2] * /* w[23] */ -11.6543208113f;
	// End of neuron

	m[24] = x[3] * /* w[24] */ -4.34444810646f;
	m[25] = x[3] * /* w[25] */ 2.12816200391f;
	m[26] = x[3] * /* w[26] */ -4.18979325266f;
	m[27] = x[3] * /* w[27] */ -22.8563104891f;
	m[28] = x[3] * /* w[28] */ -4.18004673958f;
	m[29] = x[3] * /* w[29] */ -7.19816319053f;
	m[30] = x[3] * /* w[30] */ -17.0865643717f;
	m[31] = x[3] * /* w[31] */ -16.1455630355f;
	// End of neuron

	m[32] = x[4] * /* w[32] */ 0.0107167340764f;
	m[33] = x[4] * /* w[33] */ 0.634098484844f;
	m[34] = x[4] * /* w[34] */ 0.033151596556f;
	m[35] = x[4] * /* w[35] */ 0.0170320059306f;
	m[36] = x[4] * /* w[36] */ -0.0467279195979f;
	m[37] = x[4] * /* w[37] */ -0.0946925839788f;
	m[38] = x[4] * /* w[38] */ -0.226869553446f;
	m[39] = x[4] * /* w[39] */ 0.62265266015f;
	// End of neuron

	m[40] = x[5] * /* w[40] */ 2.09750776005f;
	m[41] = x[5] * /* w[41] */ -3.87409913305f;
	m[42] = x[5] * /* w[42] */ 1.12151350615f;
	m[43] = x[5] * /* w[43] */ 2.70559674217f;
	m[44] = x[5] * /* w[44] */ 0.943739260038f;
	m[45] = x[5] * /* w[45] */ 8.86852761994f;
	m[46] = x[5] * /* w[46] */ 23.1745546849f;
	m[47] = x[5] * /* w[47] */ -1.25154111618f;
	// End of neuron

	m[48] = x[6] * /* w[48] */ 0.130655182893f;
	m[49] = x[6] * /* w[49] */ 6.93343824625f;
	m[50] = x[6] * /* w[50] */ 0.267395584078f;
	m[51] = x[6] * /* w[51] */ -2.11073587695f;
	m[52] = x[6] * /* w[52] */ 0.205538581334f;
	m[53] = x[6] * /* w[53] */ 0.626912743461f;
	m[54] = x[6] * /* w[54] */ 3.98424913504f;
	m[55] = x[6] * /* w[55] */ 22.3827561854f;
	// End of neuron

	m[56] = x[7] * /* w[56] */ 0.565070758149f;
	m[57] = x[7] * /* w[57] */ 3.17660984743f;
	m[58] = x[7] * /* w[58] */ 0.523438799732f;
	m[59] = x[7] * /* w[59] */ -1.91737267766f;
	m[60] = x[7] * /* w[60] */ 0.227814025876f;
	m[61] = x[7] * /* w[61] */ 8.08137693507f;
	m[62] = x[7] * /* w[62] */ 9.47520177622f;
	m[63] = x[7] * /* w[63] */ 12.8123251806f;
	// End of neuron

	m[64] = x[8] * /* w[64] */ 1.87281403405f;
	m[65] = x[8] * /* w[65] */ 3.29051334914f;
	m[66] = x[8] * /* w[66] */ 0.920256553503f;
	m[67] = x[8] * /* w[67] */ 8.02193844046f;
	m[68] = x[8] * /* w[68] */ 1.1502511644f;
	m[69] = x[8] * /* w[69] */ 15.6874861374f;
	m[70] = x[8] * /* w[70] */ 23.8161870794f;
	m[71] = x[8] * /* w[71] */ 5.31020736442f;
	// End of neuron

	a[0] = m[0] + m[8];
	a[1] = m[1] + m[9];
	a[2] = m[2] + m[10];
	a[3] = m[3] + m[11];
	a[4] = m[4] + m[12];
	a[5] = m[5] + m[13];
	a[6] = m[6] + m[14];
	a[7] = m[7] + m[15];
	// End of neuron

	a[0] = a[0] + m[16];
	a[1] = a[1] + m[17];
	a[2] = a[2] + m[18];
	a[3] = a[3] + m[19];
	a[4] = a[4] + m[20];
	a[5] = a[5] + m[21];
	a[6] = a[6] + m[22];
	a[7] = a[7] + m[23];
	// End of neuron

	a[0] = a[0] + m[24];
	a[1] = a[1] + m[25];
	a[2] = a[2] + m[26];
	a[3] = a[3] + m[27];
	a[4] = a[4] + m[28];
	a[5] = a[5] + m[29];
	a[6] = a[6] + m[30];
	a[7] = a[7] + m[31];
	// End of neuron

	a[0] = a[0] + m[32];
	a[1] = a[1] + m[33];
	a[2] = a[2] + m[34];
	a[3] = a[3] + m[35];
	a[4] = a[4] + m[36];
	a[5] = a[5] + m[37];
	a[6] = a[6] + m[38];
	a[7] = a[7] + m[39];
	// End of neuron

	a[0] = a[0] + m[40];
	a[1] = a[1] + m[41];
	a[2] = a[2] + m[42];
	a[3] = a[3] + m[43];
	a[4] = a[4] + m[44];
	a[5] = a[5] + m[45];
	a[6] = a[6] + m[46];
	a[7] = a[7] + m[47];
	// End of neuron

	a[0] = a[0] + m[48];
	a[1] = a[1] + m[49];
	a[2] = a[2] + m[50];
	a[3] = a[3] + m[51];
	a[4] = a[4] + m[52];
	a[5] = a[5] + m[53];
	a[6] = a[6] + m[54];
	a[7] = a[7] + m[55];
	// End of neuron

	a[0] = a[0] + m[56];
	a[1] = a[1] + m[57];
	a[2] = a[2] + m[58];
	a[3] = a[3] + m[59];
	a[4] = a[4] + m[60];
	a[5] = a[5] + m[61];
	a[6] = a[6] + m[62];
	a[7] = a[7] + m[63];
	// End of neuron

	a[0] = a[0] + m[64];
	a[1] = a[1] + m[65];
	a[2] = a[2] + m[66];
	a[3] = a[3] + m[67];
	a[4] = a[4] + m[68];
	a[5] = a[5] + m[69];
	a[6] = a[6] + m[70];
	a[7] = a[7] + m[71];
	// End of neuron

	a[0] = a[0] + /* w[72] */ -0.719199522387f;
	a[1] = a[1] + /* w[73] */ -2.06342122093f;
	a[2] = a[2] + /* w[74] */ -0.60300544722f;
	a[3] = a[3] + /* w[75] */ 1.92784485423f;
	a[4] = a[4] + /* w[76] */ -0.421256623345f;
	a[5] = a[5] + /* w[77] */ 6.4577991592f;
	a[6] = a[6] + /* w[78] */ 2.41647516396f;
	a[7] = a[7] + /* w[79] */ 2.44254634283f;
	// End of neuron

	n[0] = SIGMOID(a[0], 0.5f);
	n[1] = SIGMOID(a[1], 0.5f);
	n[2] = SIGMOID(a[2], 0.5f);
	n[3] = SIGMOID(a[3], 0.5f);
	n[4] = SIGMOID(a[4], 0.5f);
	n[5] = SIGMOID(a[5], 0.5f);
	n[6] = SIGMOID(a[6], 0.5f);
	n[7] = SIGMOID(a[7], 0.5f);
	// End of layer

	m[0] = n[0] * /* w[80] */ 16.9426769133f;
	// End of neuron

	m[1] = n[1] * /* w[81] */ 5.84684052145f;
	// End of neuron

	m[2] = n[2] * /* w[82] */ 3.20492325767f;
	// End of neuron

	m[3] = n[3] * /* w[83] */ -3.63727115847f;
	// End of neuron

	m[4] = n[4] * /* w[84] */ 1.16450402199f;
	// End of neuron

	m[5] = n[5] * /* w[85] */ -4.32809534521f;
	// End of neuron

	m[6] = n[6] * /* w[86] */ -2.54277551054f;
	// End of neuron

	m[7] = n[7] * /* w[87] */ -2.70512210716f;
	// End of neuron

	a[0] = m[0] + m[1];
	// End of neuron

	a[0] = a[0] + m[2];
	// End of neuron

	a[0] = a[0] + m[3];
	// End of neuron

	a[0] = a[0] + m[4];
	// End of neuron

	a[0] = a[0] + m[5];
	// End of neuron

	a[0] = a[0] + m[6];
	// End of neuron

	a[0] = a[0] + m[7];
	// End of neuron

	a[0] = a[0] + /* w[88] */ 2.25468477545f;
	// End of neuron

	y[0] = SIGMOID(a[0], 0.5f);
	// End of layer

	return;
}
