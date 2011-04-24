{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 848.0, 44.0, 1334.0, 998.0 ],
		"bglocked" : 0,
		"defrect" : [ 848.0, 44.0, 1334.0, 998.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 1,
		"default_fontsize" : 12.0,
		"default_fontface" : 0,
		"default_fontname" : "Andale Mono",
		"gridonopen" : 0,
		"gridsize" : [ 8.0, 8.0 ],
		"gridsnaponopen" : 0,
		"toolbarvisible" : 1,
		"boxanimatetime" : 200,
		"imprint" : 0,
		"enablehscroll" : 1,
		"enablevscroll" : 1,
		"devicewidth" : 0.0,
		"boxes" : [ 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "This gate is again trying to disconnect some logic when we start 7up",
					"linecount" : 4,
					"patching_rect" : [ 952.0, 763.0, 163.0, 60.0 ],
					"id" : "obj-80",
					"textcolor" : [ 0.87451, 0.027451, 0.027451, 1.0 ],
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"frgb" : [ 0.87451, 0.027451, 0.027451, 1.0 ],
					"numoutlets" : 0,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"patching_rect" : [ 556.0, 36.0, 25.0, 25.0 ],
					"id" : "obj-79",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"comment" : "accepts UI ignoreclick and readonly messsages"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "1",
					"patching_rect" : [ 544.0, 313.0, 32.5, 18.0 ],
					"id" : "obj-78",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set",
					"patching_rect" : [ 283.0, 375.0, 32.5, 18.0 ],
					"id" : "obj-77",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loadbang",
					"patching_rect" : [ 10.0, 152.0, 68.0, 20.0 ],
					"id" : "obj-63",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"patching_rect" : [ 62.0, 181.0, 20.0, 20.0 ],
					"id" : "obj-46",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "\"monome 128 (m128-400)\"",
					"linecount" : 8,
					"patching_rect" : [ 214.0, 330.0, 32.5, 112.0 ],
					"id" : "obj-76",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "gate 1",
					"patching_rect" : [ 894.0, 776.0, 54.0, 20.0 ],
					"id" : "obj-51",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "This input is tied to 7ups start button. The start button will send 1 when starting and signal this patch to get out of the way",
					"linecount" : 6,
					"patching_rect" : [ 500.0, 95.0, 177.0, 87.0 ],
					"id" : "obj-48",
					"textcolor" : [ 0.87451, 0.027451, 0.027451, 1.0 ],
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"frgb" : [ 0.87451, 0.027451, 0.027451, 1.0 ],
					"numoutlets" : 0,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "This part is trying to release the receive port after successful negotiation. Necessary for 7up to be able to gain it when we click connect",
					"linecount" : 8,
					"patching_rect" : [ 866.0, 8.0, 163.0, 114.0 ],
					"id" : "obj-37",
					"textcolor" : [ 0.87451, 0.027451, 0.027451, 1.0 ],
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"frgb" : [ 0.87451, 0.027451, 0.027451, 1.0 ],
					"numoutlets" : 0,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "s ---rcvport",
					"patching_rect" : [ 948.0, 428.0, 97.0, 20.0 ],
					"id" : "obj-56",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 0,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "s ---rcvport",
					"patching_rect" : [ 869.0, 227.0, 97.0, 20.0 ],
					"id" : "obj-75",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 0,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "1",
					"patching_rect" : [ 821.0, 69.0, 32.5, 18.0 ],
					"id" : "obj-74",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "toggle",
					"patching_rect" : [ 635.0, 18.0, 20.0, 20.0 ],
					"id" : "obj-73",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "int" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "0",
					"patching_rect" : [ 656.0, 72.0, 32.5, 18.0 ],
					"id" : "obj-72",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route 1 0",
					"patching_rect" : [ 657.0, 44.0, 75.0, 20.0 ],
					"id" : "obj-71",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 3,
					"outlettype" : [ "", "", "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "port 14773",
					"patching_rect" : [ 869.0, 205.0, 83.0, 18.0 ],
					"id" : "obj-70",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "port 0",
					"patching_rect" : [ 757.0, 45.0, 54.0, 18.0 ],
					"id" : "obj-69",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "gate 1",
					"patching_rect" : [ 815.0, 166.0, 54.0, 20.0 ],
					"id" : "obj-68",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "r ---rcvport",
					"patching_rect" : [ 848.0, 138.0, 97.0, 20.0 ],
					"id" : "obj-66",
					"numinlets" : 0,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"patching_rect" : [ 477.0, 194.0, 25.0, 25.0 ],
					"id" : "obj-67",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"patching_rect" : [ 60.0, 802.0, 25.0, 25.0 ],
					"id" : "obj-65",
					"numinlets" : 1,
					"numoutlets" : 0,
					"comment" : "Prefix"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route /sys/prefix /sys/port port",
					"patching_rect" : [ 96.0, 760.0, 241.0, 20.0 ],
					"id" : "obj-61",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 4,
					"outlettype" : [ "", "", "", "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"patching_rect" : [ 121.0, 802.0, 25.0, 25.0 ],
					"id" : "obj-55",
					"numinlets" : 1,
					"numoutlets" : 0,
					"comment" : "Host port"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"patching_rect" : [ 91.0, 802.0, 25.0, 25.0 ],
					"id" : "obj-52",
					"numinlets" : 1,
					"numoutlets" : 0,
					"comment" : "Application Port"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"patching_rect" : [ 704.0, 602.0, 20.0, 20.0 ],
					"id" : "obj-42",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "pak #1 5555",
					"patching_rect" : [ 648.0, 666.0, 90.0, 20.0 ],
					"id" : "obj-36",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sprintf script newobject newobj @text \\\"zeroconf.service @name %s @port %s @type _osc._udp\\\"",
					"linecount" : 2,
					"patching_rect" : [ 432.0, 714.0, 594.0, 33.0 ],
					"id" : "obj-50",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "i",
					"patching_rect" : [ 792.0, 666.0, 32.5, 20.0 ],
					"id" : "obj-53",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "thispatcher",
					"patching_rect" : [ 592.0, 762.0, 90.0, 20.0 ],
					"id" : "obj-47",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"fontsize" : 12.0,
					"save" : [ "#N", "thispatcher", ";", "#Q", "end", ";" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/sys/port $1",
					"patching_rect" : [ 256.0, 530.0, 97.0, 18.0 ],
					"id" : "obj-40",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"patching_rect" : [ 896.0, 674.0, 20.0, 20.0 ],
					"id" : "obj-64",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sel 1",
					"patching_rect" : [ 864.0, 642.0, 47.0, 20.0 ],
					"id" : "obj-62",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 2,
					"outlettype" : [ "bang", "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t 1",
					"patching_rect" : [ 944.0, 562.0, 32.0, 20.0 ],
					"id" : "obj-10",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/test/confirm",
					"patching_rect" : [ 816.0, 562.0, 104.0, 18.0 ],
					"id" : "obj-43",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set 0",
					"patching_rect" : [ 736.0, 562.0, 47.0, 18.0 ],
					"id" : "obj-24",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sel /test/ping /test/confirm",
					"patching_rect" : [ 816.0, 530.0, 212.0, 20.0 ],
					"id" : "obj-11",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 3,
					"outlettype" : [ "bang", "bang", "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "accum",
					"patching_rect" : [ 800.0, 594.0, 47.0, 20.0 ],
					"id" : "obj-19",
					"numinlets" : 3,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "del 100",
					"patching_rect" : [ 752.0, 482.0, 61.0, 20.0 ],
					"id" : "obj-14",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/test/ping",
					"patching_rect" : [ 800.0, 402.0, 83.0, 18.0 ],
					"id" : "obj-17",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "udpsend localhost 17812",
					"patching_rect" : [ 944.0, 482.0, 176.0, 20.0 ],
					"id" : "obj-21",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 0,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "port $1",
					"patching_rect" : [ 928.0, 402.0, 61.0, 18.0 ],
					"id" : "obj-25",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b i b b",
					"patching_rect" : [ 880.0, 347.0, 75.0, 20.0 ],
					"id" : "obj-27",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 4,
					"outlettype" : [ "bang", "int", "bang", "bang" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "udpreceive 17812",
					"patching_rect" : [ 816.0, 482.0, 126.0, 20.0 ],
					"id" : "obj-28",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "+ 10000",
					"patching_rect" : [ 880.0, 322.0, 61.0, 20.0 ],
					"id" : "obj-30",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "random 30000",
					"patching_rect" : [ 865.0, 286.0, 97.0, 20.0 ],
					"id" : "obj-31",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loadbang",
					"patching_rect" : [ 863.0, 249.0, 68.0, 20.0 ],
					"id" : "obj-32",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"patching_rect" : [ 656.0, 402.0, 20.0, 20.0 ],
					"id" : "obj-15",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "change",
					"patching_rect" : [ 576.0, 370.0, 54.0, 20.0 ],
					"id" : "obj-8",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 3,
					"outlettype" : [ "", "int", "int" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/sys/info",
					"patching_rect" : [ 16.0, 562.0, 75.0, 18.0 ],
					"id" : "obj-54",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"patching_rect" : [ 265.0, 238.971832, 142.0, 15.0 ],
					"presentation" : 1,
					"id" : "obj-44",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 0,
					"presentation_rect" : [ 230.0, 13.971832, 252.0, 15.0 ],
					"fontsize" : 7.422506
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set",
					"patching_rect" : [ 270.0, 355.0, 32.5, 18.0 ],
					"id" : "obj-41",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set prefix: $1",
					"patching_rect" : [ 432.0, 290.0, 111.0, 18.0 ],
					"id" : "obj-39",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"patching_rect" : [ 264.0, 218.971832, 142.0, 16.0 ],
					"presentation" : 1,
					"id" : "obj-38",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 0,
					"presentation_rect" : [ 230.0, 16.971832, 251.0, 16.0 ],
					"fontsize" : 8.393172
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "textbutton",
					"patching_rect" : [ 195.0, 215.0, 68.0, 17.0 ],
					"bgoncolor" : [ 0.658824, 0.658824, 0.658824, 1.0 ],
					"presentation" : 1,
					"id" : "obj-5",
					"rounded" : 4.0,
					"numinlets" : 1,
					"textoncolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"fontname" : "Andale Mono",
					"numoutlets" : 3,
					"text" : "connect",
					"bgovercolor" : [ 0.8, 0.8, 0.8, 1.0 ],
					"outlettype" : [ "", "", "int" ],
					"bgcolor" : [ 1.0, 1.0, 1.0, 1.0 ],
					"presentation_rect" : [ 154.0, 12.0, 68.0, 17.0 ],
					"textoveroncolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"fontsize" : 9.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b b",
					"patching_rect" : [ 208.0, 274.0, 47.0, 20.0 ],
					"id" : "obj-9",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 2,
					"outlettype" : [ "bang", "bang" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"hint" : "",
					"patching_rect" : [ 320.0, 402.0, 25.0, 25.0 ],
					"id" : "obj-4",
					"numinlets" : 1,
					"numoutlets" : 0,
					"comment" : "connected"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"hint" : "disconnected",
					"patching_rect" : [ 352.0, 402.0, 25.0, 25.0 ],
					"id" : "obj-3",
					"numinlets" : 1,
					"numoutlets" : 0,
					"comment" : "disconnected"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "\"monome 128 (m128-400)\"",
					"linecount" : 2,
					"patching_rect" : [ 112.0, 386.0, 113.0, 31.0 ],
					"id" : "obj-23",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"patching_rect" : [ 112.0, 338.0, 20.0, 20.0 ],
					"id" : "obj-12",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"hint" : "connect",
					"patching_rect" : [ 448.0, 194.0, 25.0, 25.0 ],
					"id" : "obj-60",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"comment" : "connect"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"hint" : "osc out",
					"patching_rect" : [ 288.0, 402.0, 25.0, 25.0 ],
					"id" : "obj-59",
					"numinlets" : 1,
					"numoutlets" : 0,
					"comment" : "osc out"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "gate",
					"patching_rect" : [ 384.0, 610.0, 39.0, 20.0 ],
					"id" : "obj-58",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"hint" : "osc in",
					"patching_rect" : [ 416.0, 194.0, 25.0, 25.0 ],
					"id" : "obj-57",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"comment" : "osc in"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/sys/prefix #1",
					"patching_rect" : [ 96.0, 562.0, 111.0, 18.0 ],
					"id" : "obj-49",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set not connected",
					"patching_rect" : [ 288.0, 322.0, 133.0, 18.0 ],
					"id" : "obj-45",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "sel 0 1",
					"patching_rect" : [ 320.0, 290.0, 61.0, 20.0 ],
					"id" : "obj-35",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 3,
					"outlettype" : [ "bang", "bang", "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"patching_rect" : [ 264.0, 194.971832, 142.0, 16.0 ],
					"presentation" : 1,
					"id" : "obj-33",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 0,
					"presentation_rect" : [ 230.0, 8.971832, 252.0, 16.0 ],
					"fontsize" : 8.512057
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b b s",
					"patching_rect" : [ 112.0, 498.0, 61.0, 20.0 ],
					"id" : "obj-29",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 3,
					"outlettype" : [ "bang", "bang", "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "== 17812",
					"patching_rect" : [ 576.0, 402.0, 68.0, 20.0 ],
					"id" : "obj-7",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "/sys/port 14773",
					"patching_rect" : [ 256.0, 562.0, 146.0, 18.0 ],
					"id" : "obj-26",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route /sys/prefix /sys/port /sys/id",
					"patching_rect" : [ 527.0, 243.0, 263.0, 20.0 ],
					"id" : "obj-18",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 4,
					"outlettype" : [ "", "", "", "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "name $1, bang",
					"patching_rect" : [ 112.0, 418.0, 104.0, 18.0 ],
					"id" : "obj-13",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "set $1",
					"patching_rect" : [ 423.0, 419.0, 54.0, 18.0 ],
					"id" : "obj-22",
					"numinlets" : 2,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "udpreceive 17812",
					"patching_rect" : [ 529.0, 209.0, 126.0, 20.0 ],
					"id" : "obj-20",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "udpsend localhost 17812",
					"patching_rect" : [ 160.0, 706.0, 180.0, 20.0 ],
					"id" : "obj-16",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 0,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zeroconf.resolve @domain local. @type _monome-osc._udp",
					"patching_rect" : [ 112.0, 450.0, 399.0, 20.0 ],
					"id" : "obj-6",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "umenu",
					"types" : [  ],
					"patching_rect" : [ 85.0, 215.0, 106.0, 17.0 ],
					"presentation" : 1,
					"id" : "obj-2",
					"rounded" : 4,
					"textcolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"numinlets" : 1,
					"arrowcolor" : [ 1.0, 1.0, 1.0, 1.0 ],
					"framecolor" : [ 0.25098, 0.25098, 0.25098, 1.0 ],
					"fontname" : "Andale Mono",
					"numoutlets" : 3,
					"outlettype" : [ "int", "", "" ],
					"presentation_rect" : [ 13.0, 11.674528, 135.0, 17.0 ],
					"align" : 1,
					"items" : [ "monome 128 (m128-400)", ",", "monome 256 (m0000146)" ],
					"fontsize" : 9.0,
					"arrowframe" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zeroconf.browser @type _monome-osc._udp @domain local.",
					"patching_rect" : [ 96.0, 162.0, 399.0, 20.0 ],
					"id" : "obj-1",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zeroconf.service @name #1 @port 5555 @type _osc._udp",
					"patching_rect" : [ 17.0, 127.0, 383.0, 20.0 ],
					"id" : "obj-34",
					"numinlets" : 1,
					"fontname" : "Andale Mono",
					"numoutlets" : 0,
					"fontsize" : 12.0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "panel",
					"patching_rect" : [ 624.0, 5.0, 425.0, 185.0 ],
					"id" : "obj-81",
					"numinlets" : 1,
					"numoutlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "panel",
					"patching_rect" : [ 807.0, 753.0, 359.0, 114.0 ],
					"id" : "obj-82",
					"numinlets" : 1,
					"numoutlets" : 0
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-9", 1 ],
					"destination" : [ "obj-76", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-78", 0 ],
					"destination" : [ "obj-35", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-78", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-63", 0 ],
					"destination" : [ "obj-46", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-46", 0 ],
					"destination" : [ "obj-2", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 1 ],
					"destination" : [ "obj-76", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 2 ],
					"destination" : [ "obj-22", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-41", 0 ],
					"destination" : [ "obj-33", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-25", 0 ],
					"destination" : [ "obj-56", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-61", 2 ],
					"destination" : [ "obj-55", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-61", 1 ],
					"destination" : [ "obj-52", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-61", 0 ],
					"destination" : [ "obj-65", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-49", 0 ],
					"destination" : [ "obj-61", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 2 ],
					"destination" : [ "obj-61", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-26", 0 ],
					"destination" : [ "obj-61", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-50", 0 ],
					"destination" : [ "obj-47", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 0 ],
					"destination" : [ "obj-50", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-42", 0 ],
					"destination" : [ "obj-36", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-53", 0 ],
					"destination" : [ "obj-36", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-54", 0 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 1 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-45", 0 ],
					"destination" : [ "obj-44", 0 ],
					"hidden" : 1,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-41", 0 ],
					"destination" : [ "obj-38", 0 ],
					"hidden" : 1,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-39", 0 ],
					"destination" : [ "obj-38", 0 ],
					"hidden" : 1,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-39", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 1,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 1 ],
					"destination" : [ "obj-2", 0 ],
					"hidden" : 1,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 0 ],
					"destination" : [ "obj-12", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-58", 0 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 0 ],
					"destination" : [ "obj-49", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-49", 0 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-22", 0 ],
					"destination" : [ "obj-33", 0 ],
					"hidden" : 1,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 1 ],
					"destination" : [ "obj-26", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 2 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-29", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-26", 0 ],
					"destination" : [ "obj-16", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-2", 0 ],
					"hidden" : 1,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-13", 0 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 1 ],
					"destination" : [ "obj-23", 1 ],
					"hidden" : 1,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-23", 0 ],
					"destination" : [ "obj-13", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 0 ],
					"destination" : [ "obj-23", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-60", 0 ],
					"destination" : [ "obj-12", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 0 ],
					"destination" : [ "obj-45", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 0 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 1 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-57", 0 ],
					"destination" : [ "obj-58", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-15", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 0 ],
					"destination" : [ "obj-54", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-62", 1 ],
					"destination" : [ "obj-64", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-62", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-27", 2 ],
					"destination" : [ "obj-14", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-27", 0 ],
					"destination" : [ "obj-17", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-30", 0 ],
					"destination" : [ "obj-27", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-27", 1 ],
					"destination" : [ "obj-25", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-27", 3 ],
					"destination" : [ "obj-24", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-11", 1 ],
					"destination" : [ "obj-10", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-11", 0 ],
					"destination" : [ "obj-43", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-10", 0 ],
					"destination" : [ "obj-19", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-24", 0 ],
					"destination" : [ "obj-19", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 0 ],
					"destination" : [ "obj-19", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-31", 0 ],
					"destination" : [ "obj-30", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-32", 0 ],
					"destination" : [ "obj-31", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-40", 0 ],
					"destination" : [ "obj-26", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-27", 1 ],
					"destination" : [ "obj-40", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-20", 0 ],
					"destination" : [ "obj-18", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-20", 0 ],
					"destination" : [ "obj-59", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-35", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-58", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-27", 1 ],
					"destination" : [ "obj-7", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-28", 0 ],
					"destination" : [ "obj-11", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-25", 0 ],
					"destination" : [ "obj-21", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-17", 0 ],
					"destination" : [ "obj-21", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 0 ],
					"destination" : [ "obj-21", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-27", 1 ],
					"destination" : [ "obj-53", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-62", 0 ],
					"destination" : [ "obj-53", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-68", 0 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-68", 0 ],
					"destination" : [ "obj-20", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-66", 0 ],
					"destination" : [ "obj-68", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-67", 0 ],
					"destination" : [ "obj-71", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-73", 0 ],
					"destination" : [ "obj-71", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-71", 0 ],
					"destination" : [ "obj-72", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-71", 1 ],
					"destination" : [ "obj-74", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-74", 0 ],
					"destination" : [ "obj-68", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-72", 0 ],
					"destination" : [ "obj-68", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-71", 1 ],
					"destination" : [ "obj-70", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-70", 0 ],
					"destination" : [ "obj-75", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-71", 0 ],
					"destination" : [ "obj-69", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-69", 0 ],
					"destination" : [ "obj-75", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-25", 0 ],
					"destination" : [ "obj-70", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-64", 0 ],
					"destination" : [ "obj-51", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-74", 0 ],
					"destination" : [ "obj-51", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-72", 0 ],
					"destination" : [ "obj-51", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-51", 0 ],
					"destination" : [ "obj-31", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 0 ],
					"destination" : [ "obj-41", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 1 ],
					"destination" : [ "obj-76", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-76", 0 ],
					"destination" : [ "obj-22", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-35", 1 ],
					"destination" : [ "obj-77", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-77", 0 ],
					"destination" : [ "obj-44", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-79", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ]
	}

}