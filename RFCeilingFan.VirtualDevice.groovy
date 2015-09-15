/*	Virtual fan switch with on/off, low, med, high buttons. Switch take the strings off, low, med, high and makes them
	into setlevels as 0=off, 25=low, 50=med, 75=high. levels are used by smartapp that controls arduino fan relay.
	Original version: https://github.com/tomforti/relay_hampton_bay_fan/blob/master/VirtualFanSwitch.groovy
	Modified by tamttt to add light on/off function
*/ 

metadata {	
	definition (name: "RF Ceiling Fan Virtual", namespace: "tamttt", author: "tamttt") {
		capability "Switch"
		capability "Switch Level"
        capability "Momentary"
        capability "Actuator"
        //capability "Refresh"
        
		command "fanOff"
        command "fanLow"
        command "fanMedium"
        command "fanHigh"		
        //command "lightOn"
        //command "lightOff"
		command "lightPush"

		attribute "fan", "string"
        attribute "light", "string"
		attribute "fanoff", "string"
		//attribute "currentSpeed", "string"
		//attribute "lightStatus", "string"
	}

	tiles {
		
		standardTile("fan", "device.fan", width: 2, height: 2, canChangeIcon: true) {
			state "off", label:'OFF', action:"fanLow", icon:"st.Lighting.light24", backgroundColor:"#ffffff", nextState: "low"	
			state "low", label:'LOW', action:"fanMedium", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "medium"
			state "medium", label:'MEDIUM', action:"fanHigh", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "high"
			state "high", label:'HIGH', action:"fanOff", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "off"							
		}
/*		
		//displays current speed as off, low, med, high
        valueTile("currentSpeed", "device.currentSpeed", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
            state ("default", label:'${currentValue}')
        }
*/
/*		
		//Slider not show in display but kept in for trouble shooting / testing, if needed. 
		controlTile("levelSliderControl", "device.level", "slider", height: 1, width: 3, inactiveLabel: false) {
			state "level", action:"switch level.setLevel"
		}		
*/
		
        standardTile("light", "device.light", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {            
            //state "off", label: 'ON/OFF', action: "lightOn", icon:"st.Lighting.light11", backgroundColor: "#ffffff", nextState: "on"
			//state "on", label: 'ON/OFF', action: "lightOff", icon:"st.Lighting.light11", backgroundColor: "#79b821"
        	state "off", label: "ON/OFF", action: "lightPush", icon:"st.Lighting.light13"
        }
		
		standardTile("switch", "device.switch", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "off", label: "", action: "on", icon:"st.thermostat.fan-on", backgroundColor: "#ffffff", nextState: "on"
            state "on", label: "", action: "off", icon:"st.thermostat.fan-off", backgroundColor: "#79b821"		 
		}    		

		//Speed control row
        standardTile("fanLow", "device.level", inactiveLabel: false, decoration: "flat") {
            state "fanLow", label:'LOW', action:"fanLow", icon:"st.Home.home30"
        }
        standardTile("fanMedium", "device.level", inactiveLabel: false, decoration: "flat") {
            state "fanMedium", label:'MEDIUM', action:"fanMedium", icon:"st.Home.home30"
        }
        standardTile("fanHigh", "device.level", inactiveLabel: false, decoration: "flat") {
            state "fanHigh", label:'HIGH', action:"fanHigh", icon:"st.Home.home30"
        }
		
/*		
		standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat") {
			state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
		}
*/
		main(["fan"])
		details(["fan", "light", "switch", "fanLow", "fanMedium", "fanHigh"])
	}
}

def parse(String description) {
}

def on() {
	log.info "Fan turn on"
	fanMedium()
}

def off() {
	log.info "Fan turn off"
	fanOff()
}
	
//take value and turns it into string string value (off, low, med, high)
def setLevel(val){
	log.info "Set level to $val"
    sendEvent(name:"level",value:val)
    sendEvent(name:"switch.setLevel",value:val) // had to add this to work if apps subscribed to setLevel event. "Dim With Me" was one.
	if ((val >= 0) && (val < 25)) {
		log.info "Fan off"
        sendEvent(name: "switch", value: "off", isStateChange: true, display: false) 
		sendEvent(name: "fan", value: "off", isStateChange: true, display: false) 
	}
	if ((val >= 25) && (val < 50)) {
		log.info "Fan low" 
        sendEvent(name: "switch", value: "on", isStateChange: true, display: false) 
		sendEvent(name: "fan", value: "low", isStateChange: true, display: false) 
	}
	if ((val >= 50) && (val < 75)) {
		log.info "Fan medium"
        sendEvent(name: "switch", value: "on", isStateChange: true, display: false) 
		sendEvent(name: "fan", value: "medium", isStateChange: true, display: false) 
	}
	if (val >= 75) {
		log.info "Fan high"
        sendEvent(name: "switch", value: "on", isStateChange: true, display: false) 
		sendEvent(name: "fan", value: "high", isStateChange: true, display: false) 
	}
}

def fanLow() {		
    setLevel(25)    
	//sendEvent(name: "fan", value: "low", isStateChange: true, display: false)      
    //sendEvent(name: "currentSpeed", value: "LOW" as String)      
}

def fanMedium() {	
    setLevel(50)
	//log.info "Fan medium"    
	//sendEvent(name: "fan", value: "medium", isStateChange: true, display: false) 
	//sendEvent(name: "currentSpeed", value: "MEDIUM" as String)
}

def fanHigh() {	
    setLevel(75)
	//log.info "Fan high"    
	//sendEvent(name: "fan", value: "high", isStateChange: true, display: false)   
	//sendEvent(name: "currentSpeed", value: "HIGH" as String)	
}

def fanOff() {	
    setLevel(0)
	//log.info "Fan off"    
	//sendEvent(name: "fan", value: "off", isStateChange: true, display: false)   
	//sendEvent(name: "currentSpeed", value: "OFF" as String)	
}

def lightPush() {
	sendEvent(name: "light", value: "on", isStateChange: true, display: false)
    log.info "Light push"
    //sendEvent(name: "lightStatus", value: "ON" as String)
}

/*
def lightOn() {
	sendEvent(name: "light", value: "on", isStateChange: true, display: false)
    log.info "Light on"
    //sendEvent(name: "lightStatus", value: "ON" as String)
}

def lightOff() {
	sendEvent(name: "light", value: "off", isStateChange: true, display: false)
    log.info "Light off"
    //sendEvent(name: "lightStatus", value: "ON" as String)
}
*/




