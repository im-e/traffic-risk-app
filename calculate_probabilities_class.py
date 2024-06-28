import pandas as pd

file = "US_Accidents_March23.csv//US_Accidents_March23.csv"
california = pd.read_csv(data_clean.csv)


class Accident_Calculator:
    def __init__(self, city):
        self.city = city
        self.city_data = california[(california["City"] == self.city)]
        
        self.time_probability = 0 #I added the probabilities together here but might be better to take a mean
        self.city_difference = 0

    #I have included some calculations for weather but not included them in the probability indicator at the end as they don't take into
    #account how likely that weather is
    def compare_temp(self, temp):
        self.min_temp = 12
        self.max_temp = 22
        
        temp_data = california['Temperature(C)']

        av_temp = self.city_data[(temp_data >= self.min_temp) & (temp_data <= self.max_temp)]
        
        if temp < self.min_temp:
            today_temp = self.city_data[(temp_data< self.min_temp)]['ID'].count()
        elif temp > self.max_temp:
            today_temp = self.city_data[(temp_data > self.max_temp)]['ID'].count()
        
        else:
            return 0 
        
        return today_temp, av_temp['ID'].count()
    
    
    def compare_pressure(self, pressure):
        self.min_press = 75.23
        self.max_press = 76.17
        
        press_data = california['Pressure(cm)']

        av_press = self.city_data[(press_data >= self.min_press) & (press_data <= self.max_press)]
        
        if pressure < self.min_press:
            today_press = self.city_data[(press_data< self.min_press)]['ID'].count()
        elif press > self.max_press:
            today_press = self.city_data[(press_data > self.max_press)]['ID'].count()
        
        else:
            return 0 
        
        return today_press, av_press['ID'].count()
    
    def compare_humidity(self, humidity):
        self.min_hum = 40
        self.max_hum = 78
        
        hum_data = california['Humidity(%)']

        av_hum = self.city_data[(hum_data >= self.min_hum) & (hum_data <= self.max_hum)]
        
        if humidity < self.min_hum:
            today_hum = self.city_data[(hum_data< self.min_hum)]['ID'].count()
        elif press > self.max_hum:
            today_hum = self.city_data[(hum_data > self.max_hum)]['ID'].count()
        
        else:
            return 0 
        
        return today_hum, av_hum['ID'].count()     
    
    # Calculations for hour/month/day of week
    
     #compare hour to average hour
    def compare_hour(self, hour):
        
        accidents_hour = self.city_data.groupby('Hour')['ID'].count()
        av_hour = accidents_hour.mean()
        
        today_hour = self.city_data[(self.city_data["Hour"]==hour)]["ID"].count()
        
        percent_change = (today_hour -av_hour)/av_hour *100  
        
        self.time_probability+= percent_change
       
        
        return av_hour, today_hour, percent_change  
    
     # compare month to average month
    def compare_month(self, month):
        
        accidents_month = self.city_data.groupby('Month')['ID'].count()
        av_month = accidents_month.mean()
        
        today_month = self.city_data[(self.city_data["Month"]==month)]["ID"].count()
        
        percent_change = (today_month -av_month)/av_month*100  
        
        self.time_probability+= percent_change
       
        
        return av_month, today_month, percent_change  
    
   
    #compare day of week to average day of week
    def compare_day(self, day):
         accidents_day = self.city_data.groupby([self.city_data['Start_Time'].dt.dayofweek])['ID'].count()
         av_day = accidents_day.mean()

         today_day = self.city_data[(self.city_data['Start_Time'].dt.dayofweek==day)]["ID"].count()

         percent_change = (today_day -av_day)/av_day *100
        
         self.time_probability+= percent_change       


         return av_day, today_day, percent_change

        
        # General comparison of city to average for the state. This doesn't take into account city population
    
    def compare_city_to_state(self):
        #calculates how more dangerous a city it compared to the city average
        accidents_city = california.groupby('City')['ID'].count()
        cali_av =accidents_city.mean()
        
        city_accidents = self.city_data['ID'].count()
        
        self.city_difference = (city_accidents-cali_av)/cali_av *100  
       
        
        return cali_av, city_accidents, self.city_difference
    
    #Gets out put from city and time data
    def get_probabilities(self,hour="none",day="none", month="none"):
        
        if type(day)==int:
            self.compare_day(day)
        if type(hour)==int:
            self.compare_hour(hour)
        if type(month)==int:
            self.compare_month(month)
        
        if self.time_probability < 0:
            print(f"Based on the time and date,today your chance of an accident is {abs(round(self.time_probability,2))}% less likely than normal in {self.city}")
        elif self.time_probability > 0:
            print(f"Based on the time and date,today your chance of an accident is {(round(self.time_probability,2))}% more likely than normal in {self.city}")
        elif self.time_probability == 0:
            print(f"Based on the time and date, today you have an average chance of an accident in {self.city}")
        self.time_probability = 0
            
            
        if type(self.city)==str and self.city in california['City'].tolist():
            city_prob = self.compare_city_to_state()
            if self.city_difference < 0:
                print(f"{self.city} is {abs(round(self.city_difference,2))}% less likely to have an accident than average in California")
            
            elif self.city_difference > 0:
                print(f"{self.city} is {round(self.city_difference,2)}% more likely to have an accident than average in California")
                
            elif self.city_difference == 0:
                print(f"{self.city} has the same accident risk as average in California")
                
            self.city_difference = 0
            

   
a = Accident_Calculator("Yuba City")
  
a.get_probabilities(day=3) 
# Output: Based on the time and date,today your chance of an accident is 6.99% more likely than normal in Yuba City
#Yuba City is 8.05% more likely to have an accident than average in California    

a.get_probabilities(2,3,5)
#Output: Based on the time and date,today your chance of an accident is 55.77% less likely than normal
#Yuba City is 8.05% more likely to have an accident than average in California
        
            
        
            
        
