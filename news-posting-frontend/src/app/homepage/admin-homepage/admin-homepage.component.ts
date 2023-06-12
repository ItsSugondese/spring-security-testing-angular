import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostingService } from './posting-service/posting.service';
import { news } from 'src/app/interface';

@Component({
  selector: 'app-admin-homepage',
  templateUrl: './admin-homepage.component.html',
  styleUrls: ['./admin-homepage.component.scss']
})
export class AdminHomepageComponent implements OnInit {
 
  formHeader = {
    status: "Submit News",
    color: "black"
  };

  newsForm !: FormGroup;

  selectedFile :any = File;

  newsDetails !: news;
  constructor(private fb : FormBuilder,
    private ps : PostingService){

  }


  ngOnInit(): void {
    this.newsForm = this.fb.group({
      title: ['', [Validators.required]],
      content: ['', Validators.required],
      thumnailName: ['', Validators.required]
    });
  }
  handleSubmit(){
    if (this.newsForm.valid) {
      const formData = new FormData();
      formData.append('news', JSON.stringify(this.newsForm.value))
      formData.append('thumbnail', this.selectedFile)

      this.ps.postNews(formData).subscribe(
        (response) => {

          this.newsForm.reset();
          this.formHeader = {
            status : "Posted Successfully",
            color : "Green"
          }

          this.newsDetails = response;
          console.log(this.newsDetails)
        }
      )

      
    

    
    }
  }

  onThumbnailSelected(event  : Event){
    if(event.target instanceof HTMLInputElement && event.target != null){
      const select = event.target.files;
      if(select){
        this.selectedFile = select[0];
        console.log(this.selectedFile)
      }
    }
    
  }

  get title(){
    return this.newsForm.get('title');
  }

  get content(){
    return this.newsForm.get('content');
  }
  get thumnailName(){
      return this.newsForm.get('thumnailName');
  }
  
    
  
}
