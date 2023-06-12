import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NewsService } from './news-service/news.service';
import { news } from '../interface';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  nId !: Number;
  news !: news;

  loading: boolean = true;
  base64Data: any = [];
  retrievedImage : any;


  constructor(private router : ActivatedRoute,
    private ns : NewsService){

  }
  ngOnInit(): void {
    this.router.params.subscribe(
      (params) => {
        this.nId = params['nId']
      }
    );

    this.ns.getNews(this.nId).subscribe(
      (res) => {
        console.log(res)
        this.news = res;

        const extenstion = res.thumnailName.substring(res.thumnailName.length-3, res.thumnailName.length);
          
        this.base64Data = res.thumbnail;
        
        this.retrievedImage = 'data:image/' + extenstion + ';base64,' + this.base64Data;
        this.news.thumbnail = this.retrievedImage;
        this.loading = false;
      }
    )
  }


}
