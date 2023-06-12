import { Component, OnInit } from '@angular/core';
import { HomepageService } from './homepage-service/homepage.service';
import { news } from 'src/app/interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-homepage',
  templateUrl: './user-homepage.component.html',
  styleUrls: ['./user-homepage.component.scss']
})
export class UserHomepageComponent implements OnInit{

  allNews !: news[];

  base64Data: any = [];

  loading : boolean = true;
  retrievedImage : any;
  constructor(private hs : HomepageService,
    private router : Router) {
    
  }

  ngOnInit(): void {
    this.hs.getNews().subscribe(
      (response) => {
        
        this.allNews = response;

        console.log(this.allNews)

        for(let i=0; i<response.length; i++){
          console.log(response[i])
          const extenstion = response[i].thumnailName.substring(response[i].thumnailName.length-3, response[i].thumnailName.length);
          
          this.base64Data[i] = response[i].thumbnail;
          
          this.retrievedImage = 'data:image/' + extenstion + ';base64,' + this.base64Data[i];
          this.allNews[i].thumbnail = this.retrievedImage;
        }
        this.loading = false;
      }
    )
  }

  navigate(id : Number){
    console.log(id);
    this.router.navigate(['/userHomepage', id])
  }

}
