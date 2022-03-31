package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayHarp extends OverlayPath {
	private static final String PATH = "m -33.631305,-297.6465 -1.231916,1.2319 -4.517028,4.55809 -0.328511,-0.20531 -0.451703,-0.20534 -0.492767,-0.2053 -0.492767,-0.20533 -0.492765,-0.16425 -0.492767,-0.12311 -0.492767,-0.12315 -0.492767,-0.0821 -0.492767,-0.041 -0.492767,-0.041 -0.53383,-0.041 -0.492766,0.041 -0.492767,0.041 -0.533831,0.041 -0.492767,0.0821 -0.492767,0.12315 -0.492766,0.12311 -0.492766,0.12313 -0.492768,0.20532 -0.451702,0.20532 -0.492767,0.20531 -0.451703,0.28745 -0.451703,0.24638 -0.410639,0.28746 -0.451703,0.32851 -0.410638,0.36956 -0.369575,0.36958 -0.369575,0.36957 -0.369574,0.41065 -0.328513,0.41064 -0.287447,0.45171 -0.287447,0.45169 -0.246384,0.45171 -0.246383,0.4517 -0.20532,0.49277 -0.164255,0.45169 -0.164257,0.49277 -0.164255,0.49277 -0.08213,0.49276 -0.08213,0.53384 -0.08213,0.49276 -0.04106,0.49278 0,0.49277 0,0.53382 0.04106,0.49277 0.04106,0.49276 0.08213,0.53383 0.123191,0.49276 0.123192,0.49277 0.164257,0.49276 0.164254,0.45171 0.205321,0.49276 0.246381,0.45171 0.246386,0.4517 0.08213,0.12314 -13.427893,13.59213 -0.451703,-0.24639 -0.451703,-0.24637 -0.492767,-0.20533 -0.451703,-0.16425 -0.492767,-0.16425 -0.492767,-0.12314 -0.492767,-0.1231 -0.492768,-0.0822 -0.533829,-0.0821 -0.492766,-0.041 -0.492767,0 -0.533831,0 -0.492768,0.041 -0.492766,0.041 -0.492766,0.0821 -0.53383,0.12312 -0.451703,0.12313 -0.492767,0.16426 -0.492767,0.20532 -0.492767,0.16426 -0.451703,0.24639 -0.451702,0.24637 -0.451703,0.28745 -0.451703,0.28746 -0.410639,0.32849 -0.410639,0.36958 -0.369575,0.36958 -0.410638,0.36958 -0.328512,0.41063 -0.32851,0.41064 -0.328512,0.45169 -0.246383,0.41065 -0.287447,0.49276 -0.20532,0.4517 -0.205319,0.45171 -0.20532,0.49278 -0.164256,0.49276 -0.12319,0.49277 -0.123192,0.49276 -0.08213,0.49277 -0.04106,0.49276 -0.04106,0.53382 -0.04106,0.49277 0,0.49276 0.04106,0.53385 0.08213,0.49276 0.08213,0.49277 0.08213,0.49276 0.123192,0.49277 0.164254,0.49277 0.205321,0.49276 0.205318,0.49277 0.20532,0.45169 0.246383,0.45171 0.123192,0.20533 -8.007459,8.13063 -1.067661,1.06768 1.026598,1.06765 6.570221,7.063 -0.205319,0.24637 -0.657022,0.73916 -0.657022,0.69807 -0.657023,0.78023 -0.657022,0.73915 -0.657023,0.78022 -0.657021,0.7802 -0.657023,0.78022 -0.657021,0.82127 -0.657024,0.78021 -0.698085,0.82128 -0.615957,0.82128 -0.657024,0.82129 -0.615959,0.86234 -0.615957,0.82128 -0.615959,0.82126 -0.615958,0.82128 -0.574895,0.86235 -0.698086,0.98553 -0.739151,1.06767 -0.862341,1.19084 -0.862342,1.19086 -0.985532,1.27298 -0.985535,1.35511 -1.067661,1.43724 -1.10872,1.43723 -1.19086,1.56042 -1.19085,1.56044 -1.23192,1.60148 -1.27298,1.68363 -1.31404,1.72468 -1.31405,1.76575 -1.35511,1.80681 -1.39617,1.84788 -1.3551,1.88893 -1.43723,1.88894 -1.39618,1.97108 -1.43723,1.97106 -0.36958,0.53382 -0.4517,0.12314 -1.0266,0.36957 -1.0266,0.36957 -0.94446,0.41063 -0.94447,0.41065 -0.86234,0.45171 -0.82128,0.45169 -0.82127,0.49277 -0.73915,0.53382 -0.73915,0.53385 -0.65703,0.5749 -0.65702,0.57488 -0.5749,0.61595 -0.57488,0.65704 -0.49277,0.65701 -0.49277,0.6981 -0.41064,0.73915 -0.41064,0.73915 -0.36957,0.78021 -0.32852,0.82128 -0.24638,0.82127 -0.28744,0.82128 -0.20533,0.9034 -0.16425,0.86233 -0.16425,0.94448 -0.12312,0.94448 -0.12312,0.94446 -0.0821,1.0266 -0.041,1.0266 0,1.06766 0,1.06765 0,0.65703 -0.20531,0.3285 -0.45171,0.73915 -0.20532,0.24639 -1.64255,1.97106 -1.6015,2.17639 -1.60149,2.25851 -1.51935,2.38171 -1.4783,2.46383 -1.4783,2.62808 -1.43724,2.66916 -1.39616,2.75127 -1.35511,2.87447 -1.31405,2.91553 -1.31405,2.9566 -1.27298,3.03873 -1.23191,3.03872 -1.19085,3.12086 -1.19085,3.12085 -1.14979,3.16192 -1.10873,3.12085 -1.06765,3.16193 -1.06766,3.16192 -0.98554,3.12085 -1.02659,3.12086 -0.94447,3.07979 -0.94448,3.038726 -0.9034,2.997664 -0.86234,2.915534 -0.82128,2.874474 -0.82127,2.792344 -0.78022,2.710217 -0.78021,2.587025 -0.69809,2.545962 -0.69809,2.381706 -1.23192,4.517027 -1.14978,4.558094 -1.06767,4.640219 -0.98553,4.681284 -0.86234,4.722348 -0.78021,4.763412 -0.69809,4.804474 -0.61596,4.845539 -0.53384,4.84554 -0.4517,4.886601 -0.36956,4.927668 -0.28745,4.927668 -0.20532,4.96873 -0.16426,4.9687306 -0.041,4.9687304 0,4.9687295 0.041,4.9687322 0.16426,5.0097923 0.20532,4.968731 0.24639,4.927668 0.3285,4.96873 0.41064,4.927667 0.4517,4.886604 0.49277,4.886602 0.5749,4.845539 0.61595,4.84554 0.65703,4.763411 0.69808,4.763411 0.78022,4.681281 0.82127,4.640222 0.82128,4.599171 0.9034,4.558097 0.49277,2.258513 0.4517,2.258502 0.49277,2.25852 0.53383,2.258513 0.49278,2.258514 0.53382,2.258531 0.53383,2.258513 0.5749,2.258514 0.5749,2.217456 0.57488,2.258509 0.5749,2.21747 0.61596,2.25852 0.65702,2.21745 0.65703,2.21745 0.65702,2.21745 0.69809,2.17639 0.69808,2.21744 0.69808,2.17639 0.73915,2.21745 0.78021,2.17639 0.78022,2.17639 0.78021,2.17639 0.82128,2.13531 0.86234,2.17639 0.86234,2.13532 0.86235,2.13531 0.9034,2.13534 0.94447,2.13532 0.94446,2.09425 0.98553,2.13533 0.98554,2.09426 0.65702,1.31404 -0.0821,0.28745 -0.20532,1.06767 -0.20531,1.10871 -0.12314,1.06766 -0.12312,1.10873 -0.0821,1.02659 -0.041,1.06768 0,1.06766 0.041,1.02659 0.0821,1.02658 0.12312,1.02662 0.16426,0.98553 0.20533,0.98553 0.24637,0.94447 0.32851,0.94446 0.32852,0.90342 0.41064,0.9034 0.4517,0.86234 0.49277,0.82129 0.57488,0.78021 0.61596,0.7802 0.65703,0.6981 0.69809,0.69808 0.78021,0.61597 0.82128,0.57488 0.9034,0.53383 0.9034,0.49277 0.98554,0.41064 1.02658,0.36957 1.10873,0.32852 1.10872,0.24638 1.19086,0.20533 0.98553,0.1231 2.87448,4.8045 5.95425,8.78767 2.01213,2.79235 1.84788,2.62809 1.76573,2.42276 1.64257,2.21746 1.51936,2.05318 1.39617,1.88895 1.31404,1.76573 1.19085,1.6015 1.10873,1.43724 1.02659,1.35511 0.94447,1.23192 0.86235,1.14977 0.78021,1.02662 0.73915,0.94447 0.69809,0.9034 0.61595,0.82129 0.53383,0.73914 0.53384,0.73915 0.49276,0.69807 0.49277,0.65704 0.41064,0.69807 0.4517,0.65704 0.41064,0.65701 0.41064,0.6981 0.41064,0.73914 0.41064,0.78021 0.4517,0.86235 0.451702,0.9034 0.492767,0.98553 0.492767,1.06767 0.574893,1.19085 0.574896,1.27298 0.615959,1.35511 0.574892,1.27299 0.574895,1.19084 0.533832,1.19086 0.492767,1.14979 0.492765,1.06766 0.451702,1.06766 0.451704,0.98553 0.410639,0.98554 0.410638,0.94447 0.369577,0.90341 0.369574,0.86234 0.328512,0.86233 0.287446,0.82127 0.287449,0.78023 0.287446,0.78021 0.246383,0.73914 0.246384,0.73916 0.246383,0.69809 0.20532,0.69808 0.164254,0.69809 0.164256,0.65701 0.164257,0.6981 0.164254,0.65701 0.123192,0.65702 0.123192,0.65703 0.123191,0.65701 0.08213,0.65704 0.08213,0.65701 0.08213,0.6981 0.08213,0.65701 0.04106,0.6981 0.08213,1.76574 0,1.56043 -0.08213,1.39617 -0.164256,1.1498 -0.20532,1.02659 -0.287446,0.78021 -0.328512,0.65702 -0.328511,0.53383 -0.410638,0.41064 -0.369576,0.32851 -0.451703,0.24639 -0.533829,0.20532 -0.574896,0.12313 -0.657022,0.12312 -0.739151,0.041 -0.780213,0 -0.821278,-0.0821 -0.903405,-0.0821 -0.903406,-0.0821 -0.944469,-0.12313 -0.985534,-0.16425 -1.026597,-0.12313 -0.985532,-0.12311 -1.06766,-0.12315 -1.0266,-0.0821 -1.06766,-0.041 -1.06766,0.041 -1.06766,0.0821 -1.06766,0.16428 -1.06767,0.24637 -1.02659,0.36958 -1.0266,0.49276 -1.68362,1.02659 -1.47829,1.06768 -1.27299,1.14978 -1.02659,1.23192 -0.82128,1.27299 -0.57489,1.31404 -0.41065,1.31405 -0.16425,1.3551 0.0411,1.35511 0.24639,1.31404 0.36957,1.27298 0.5749,1.27298 0.69809,1.19086 0.78021,1.14977 0.94447,1.10875 1.0266,1.02659 1.10871,0.98553 1.19086,0.94446 1.27298,0.90341 1.31404,0.78022 1.39617,0.73914 1.39618,0.69809 1.4783,0.5749 1.51936,0.49275 1.51936,0.41065 1.519369,0.32852 1.519364,0.24639 1.519364,0.0821 1.519363,0 1.519365,-0.12313 -0.123192,0.4517 -0.123192,0.57489 -0.08213,0.61597 -0.08213,0.61596 0,0.61594 0,0.61598 0.04106,0.69807 0.08213,0.65704 0.20532,0.65701 0.246383,0.65703 0.328512,0.61595 0.451703,0.61595 0.533829,0.57491 0.61596,0.45171 0.698084,0.41063 0.821278,0.32851 0.862342,0.24639 0.94447,0.12311 1.026598,0.041 1.108724,0 1.231917,-0.12313 1.27298,-0.20531 1.437237,-0.32851 1.396173,-0.32852 1.437236,-0.36956 1.478299,-0.41064 1.478301,-0.41065 1.478299,-0.45169 1.519365,-0.45171 1.519363,-0.49276 1.560429,-0.53384 1.560427,-0.53384 1.560428,-0.57488 1.560429,-0.61595 1.560427,-0.65704 1.601493,-0.65701 1.601493,-0.73916 1.601491,-0.73914 1.60149,-0.78021 1.560429,-0.82129 1.601492,-0.86234 1.601491,-0.9034 1.601494,-0.94447 1.601491,-0.98554 1.560427,-1.06765 1.560429,-1.06768 1.601491,-1.14978 1.519364,-1.19086 1.560428,-1.19085 1.519364,-1.31405 1.519365,-1.31402 1.4783,-1.3962 1.478299,-1.43723 1.4783,-1.51936 1.437236,-1.56042 1.355108,-1.51937 1.314045,-1.4783 1.27298,-1.43723 1.14979,-1.35511 1.149788,-1.31404 1.0674,-1.27272 0.985532,-1.23192 0.985533,-1.19086 0.903407,-1.14978 0.862341,-1.06766 0.780213,-1.06767 0.780215,-1.02659 0.739151,-0.98553 0.698085,-0.94447 0.615959,-0.90341 0.615958,-0.9034 0.574895,-0.86234 0.53383,-0.82129 0.533829,-0.78022 0.492768,-0.7802 0.451702,-0.78022 0.451704,-0.73915 0.410638,-0.69808 0.410639,-0.73916 0.369575,-0.65701 0.369575,-0.6981 0.328512,-0.65701 0.32851,-0.65704 0.328512,-0.61595 0.328511,-0.65703 0.32851,-0.61595 0.287448,-0.61595 0.328512,-0.61597 0.287446,-0.61595 0.2874484,-0.5749 0.2463819,-0.57489 0.2874485,-0.53384 0.246383,-0.49276 0.246383,-0.53383 0.2053197,-0.4517 0.2053186,-0.49277 0.2053204,-0.4517 0.2053175,-0.41064 0.1642564,-0.41064 0.1642565,-0.36958 0.1642542,-0.36958 0.041064,-0.16424 0.1231921,0.041 0.1642564,0.0821 0.1642542,0.041 0.1642565,0.0821 0.1642542,0.041 0.1642564,0.041 0.1642565,0.0821 0.1642542,0.041 0.2053197,0.041 0.1642554,0.0821 0.1642564,0.041 0.2053198,0.041 0.1642536,0.041 0.2053208,0.0821 0.1642541,0.041 0.2053209,0.041 0.2053174,0.041 0.1642565,0.041 0.2053209,0.041 0.2053186,0.041 0.2053197,0.041 0.2053186,0.041 1.4372371,0.24639 1.47829905,0.24637 1.43723698,0.24639 1.43723517,0.16425 1.4372358,0.20531 1.4372371,0.12313 1.3961726,0.12314 1.396172,0.12311 1.3961702,0.12313 1.3961726,0.0821 1.355109,0.0821 1.355108,0.0821 1.355109,0.0821 1.355109,0.041 1.314045,0.0821 1.355107,0.041 1.272981,0.041 1.314043,0.0821 1.314047,0.041 1.272981,0.0821 1.27298,0.0821 1.231916,0.0821 1.231916,0.0821 1.231918,0.0821 1.231916,0.12312 1.231916,0.12314 1.190854,0.16425 1.190853,0.16424 1.149788,0.16428 1.14979,0.24637 1.149788,0.20532 1.149789,0.24638 1.231916,0.28746 1.190855,0.16424 1.149788,0.12313 1.149788,0.0821 1.067661,0 1.067661,-0.041 1.026598,-0.12313 1.026598,-0.12311 0.94447,-0.20533 0.903403,-0.24638 0.903407,-0.24639 0.862341,-0.32851 0.780216,-0.3285 0.780213,-0.32852 0.698086,-0.36958 0.698085,-0.41063 0.657023,-0.36958 0.574896,-0.41064 0.574892,-0.41065 0.533832,-0.41062 0.451703,-0.41063 0.451702,-0.41065 0.41064,-0.36958 0.32851,-0.41064 0.328512,-0.36957 0.287447,-0.32852 0.205318,-0.36956 0.205321,-0.32851 0.205319,-0.32852 0.123192,-0.41064 0,-0.24637 0.533831,-1.19087 1.108724,-3.07979 1.642557,-4.55809 2.094258,-5.9132 2.50489,-7.22725 2.874474,-8.37702 3.244049,-9.48576 3.572561,-10.47129 3.860009,-11.37472 4.106395,-12.1549 4.35278,-12.81194 4.517021,-13.42788 4.681292,-13.87961 4.763411,-14.24917 4.88663,-14.536635 4.92767,-14.741962 4.96873,-14.783014 4.92767,-14.782996 4.88661,-14.65981 4.84554,-14.413426 4.68127,-14.1259755 4.55809,-13.6332095 4.35278,-13.140446 4.14745,-12.524488 3.90108,-11.742629 3.57255,-10.96406 3.32618,-9.978526 2.91553,-8.951928 2.54596,-7.843204 2.13534,-6.611285 1.6836,-5.215116 1.23192,-3.81894 0.57489,-1.88894 0.6981,-1.93 0.73914,-2.0532 0.78021,-2.13532 0.82129,-2.13532 0.82128,-2.25852 0.86234,-2.25852 0.90341,-2.34064 0.86234,-2.34063 0.82126,-2.3817 0.82128,-2.42277 0.82129,-2.42277 0.69809,-2.42277 0.69807,-2.42277 0.61596,-2.42277 0.49276,-2.42276 0.41065,-2.38171 0.32851,-2.3817 0.20531,-2.34064 0.0821,-2.25851 -0.041,-1.06766 0.16428,-0.65702 0.7802,-3.32617 0.36957,-3.24405 0.041,-3.16191 -0.24639,-3.03873 -0.61596,-2.91554 -0.86234,-2.79233 -1.19085,-2.6281 -1.39618,-2.50489 -1.64254,-2.34064 -1.84789,-2.17638 -0.3285,-0.32851 -0.041,-0.49278 -0.41064,-6.40596 -0.7802,-5.9132 -1.02661,-5.46149 -1.31403,-4.92767 -1.56044,-4.51703 -1.84787,-4.10639 -2.09427,-3.65469 -2.34064,-3.24404 -2.50488,-2.87449 -2.75128,-2.46382 -2.91555,-2.13533 -3.07979,-1.72468 -3.20298,-1.43724 -3.36724,-1.10872 -3.44938,-0.82128 -3.49048,-0.53383 -3.61362,-0.28745 -3.65469,-0.041 -3.69575,0.16425 -3.7368,0.32851 -3.73684,0.53384 -3.77787,0.69807 -3.73682,0.82128 -3.69575,0.9034 -3.69575,1.06768 -3.61363,1.10872 -3.57257,1.19086 -3.449375,1.19083 -3.408296,1.27299 -3.244072,1.23193 -3.161905,1.27298 -2.99767,1.2319 -2.710221,0.98555 -2.710221,0.7802 -2.710215,0.57491 -2.751284,0.3285 -2.792342,0.16426 -2.83341,-0.041 -2.833405,-0.28745 -2.915538,-0.45169 -2.915536,-0.6981 -2.997663,-0.9034 -3.038729,-1.10872 -3.120855,-1.31405 -3.120855,-1.56044 -3.244048,-1.72468 -3.244047,-1.97106 -3.367239,-2.1764 -3.367239,-2.3817 -3.490431,-2.62808 -3.531495,-2.79235 -3.572558,-3.03873 -3.69575,-3.24405 -3.736814,-3.44935 -3.818944,-3.69575 -3.901069,-3.86002 -3.9831951,-4.10637 -4.06532509,-4.27064 -4.14745341,-4.51703 -4.2295814,-4.72237 -4.311707,-4.92767 -4.4349,-5.13295 -4.517028,-5.33832 -4.599155,-5.54362 -1.067662,-1.27298 -1.067661,1.0266 -4.599154,-5.74895 -1.108727,-1.35511 z m -3.490431,107.13567 0.08213,0.0821 4.352772,3.57256 4.188517,3.16193 0,265.436957 -11.456825,19.833883 0,-289.4183 0.04106,-0.041 0.73915,-0.65701 0.615957,-0.65704 0.615959,-0.53382 0.492767,-0.45171 0.328509,-0.32852 z m 167.089026,0.41063 0.57488,0 0.32852,0.0821 0.16425,0.041 0.20533,0.16424 0.2053,0.20534 0.16427,0.16424 0.16425,0.20533 0.12319,0.20532 0.16427,0.24639 0.12319,0.20531 0.0821,0.2464 0.12318,0.24636 0.0822,0.24639 0.0821,0.24638 0.0411,0.2464 0.041,0.28745 0.0411,0.24637 0.0411,0.24639 0.0411,0.28743 0,0.2464 0.0411,0.24637 0,0.24639 0,0.24638 0,0.12313 -1.8068,0.94447 -2.71022,1.76574 -2.54595,2.05321 -2.46385,2.29958 -1.14977,1.3551 -0.73916,-1.10873 -1.23192,2.17639 -14.61876,25.3364 0,-27.67704 1.19086,-0.65703 2.3817,-1.27297 2.25852,-1.23192 2.17639,-1.23192 2.05319,-1.14978 1.97108,-1.06767 1.88894,-1.0266 1.76574,-0.9034 1.64256,-0.86234 1.56044,-0.69809 1.39616,-0.65702 1.27298,-0.49277 1.10872,-0.41063 0.98553,-0.24638 0.78023,-0.16427 z m -173.043289,5.29723 0,291.80003 -11.415763,19.75171 0,-299.35576 0.04106,-0.041 1.190853,-1.39618 1.231917,-1.31404 1.190853,-1.31405 1.149789,-1.27297 1.149789,-1.23193 1.108725,-1.19084 1.067662,-1.10874 1.067661,-1.06765 1.026598,-1.02659 0.944467,-0.98555 0.246385,-0.24638 z m 17.698536,3.36726 1.68362,1.23189 4.640219,3.0798 4.763411,2.87448 0.328511,0.20531 0,230.614772 -11.415761,19.751737 0,-257.757989 z m 127.790833,5.09191 0,31.37279 -11.415758,19.79277 0,-45.41663 2.751278,-1.31404 2.751285,-1.3551 2.669152,-1.35511 2.628093,-1.39618 0.61595,-0.3285 z m -113.254215,4.02425 1.3551077,0.78022 4.8866022,2.5049 4.9687301,2.29958 0.20532088,0.0821 0,198.051096 -11.41576088,19.751731 0,-223.469657 z m 98.717603,3.20298 0,49.3177 -11.41578,19.7928 0,-64.38815 2.545968,-0.98553 3.079796,-1.23191 2.997663,-1.27298 2.792353,-1.23193 z m -145.489375,0.20533 0,301.08046 -15.234705,26.40408 -0.04106,-0.0821 -1.971068,-2.99766 -1.930002,-3.07979 -1.888939,-3.16192 -1.888938,-3.24405 -1.847876,-3.32617 -1.847875,-3.4083 -1.806811,-3.49044 -1.765748,-3.61362 -1.765746,-3.73682 -1.765747,-3.85999 -1.683621,-3.9832 -1.724684,-4.10639 -1.642555,-4.27066 -1.642555,-4.43489 -1.642556,-4.55811 -1.601493,-4.763406 -1.60149,-4.886614 -1.56043,-5.09193 -1.56042,-5.297258 -1.51937,-5.502562 -1.51936,-5.666822 -1.43724,-5.748942 -1.31404,-5.543637 -1.14979,-5.420427 -1.06766,-5.297243 -0.94447,-5.132985 -0.86234,-5.050859 -0.69809,-4.886603 -0.61596,-4.763412 -0.53383,-4.640219 -0.4517,-4.558093 -0.32851,-4.393836 -0.24638,-4.311708 -0.20532,-4.1885162 -0.0821,-4.1063895 0,-4.02426065 0.0411,-3.90106815 0.0821,-3.8189431 0.16426,-3.7368134 0.24638,-3.654687 0.28745,-3.572558 0.32851,-3.49043 0.36958,-3.449367 0.36957,-3.326174 0.4517,-3.326177 0.49276,-3.202982 0.49277,-3.202985 0.53384,-3.120853 0.53382,-3.079792 0.53383,-3.038728 0.5749,-2.997665 0.5749,-2.915537 0.57488,-2.915536 0.61596,-2.9566 0.73915,-2.997664 0.82128,-3.120857 0.94447,-3.244047 1.0266,-3.285112 1.14979,-3.367239 1.19085,-3.408303 1.31404,-3.490431 1.35511,-3.490431 1.43724,-3.572558 1.4783,-3.572558 1.519363,-3.572558 1.601491,-3.613624 1.601491,-3.57256 1.642556,-3.57256 1.683621,-3.53149 1.724683,-3.5315 1.724683,-3.44937 1.724684,-3.40829 1.724684,-3.36725 1.724683,-3.2851 1.724683,-3.20298 1.683619,-3.12086 1.68362,-2.99766 1.642555,-2.91553 1.601491,-2.79234 1.601493,-2.66915 1.519364,-2.50489 1.4783,-2.38172 1.396174,-2.21744 1.355107,-2.05319 1.27298,-1.88894 1.14979,-1.68361 1.190853,-1.68362 1.190853,-1.64257 1.231916,-1.60149 1.231919,-1.60149 1.231916,-1.56041 1.272981,-1.51936 0.53383,-0.65703 z m 61.308388,3.53149 1.7246833,0.69809 5.0919217,1.84786 4.599155,1.43724 0,167.6227731 -11.41576,19.7517309 0,-191.357694 z m 69.644353,2.13532 0,68.617729 -11.415758,19.792794 0,-85.043283 1.026599,-0.20532 3.285106,-0.94447 3.285107,-0.94448 3.202986,-1.06765 0.61596,-0.20532 z m -55.066673,2.66915 2.587025,0.65702 5.215114,1.0266 3.613623,0.53383 0,139.329752 -11.415762,19.79279415 0,-161.33999615 z m 40.488995,1.39617 0,89.8067 -11.41576,19.751732 0,-107.998002 5.050858,-0.49277 5.297242,-0.82127 1.06766,-0.24639 z m -25.952378,1.19085 3.777879,0.36958 5.256179,0.16425 2.381704,-0.0821 0,113.336307 -11.415762,19.75173 0,-133.539747 z";
	private final Vector pathSize = new Vector(366, 599);

	public OverlayHarp(int maximumX, int maximumY) {
		super("harp", maximumX, maximumY);
		constructor(PATH, pathSize);
	}
}
